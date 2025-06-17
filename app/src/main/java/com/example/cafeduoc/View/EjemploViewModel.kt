package com.example.cafeduoc.View

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class EjemploViewModel : ViewModel() {
    fun algunaOperacionAsincrona() {
        viewModelScope.launch { // Coroutine atada al ciclo de vida del ViewModel
            Log.d("MiScope", "Iniciando trabajo en viewModelScope...")
            // ... aquí iría el código de la coroutine (ej. llamadas a suspend fun) ...
            // Si el ViewModel se destruye, esta coroutine se cancelará.
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MiScope", "ViewModel limpiado, viewModelScope cancelado.")
    }

    fun gestionarTrabajoConJob(scope: CoroutineScope) {
        val job = scope.launch {
            Log.d("Coroutines_Job", "Trabajo iniciado...")
            try {
                repeat(10) { i ->
                    if (!isActive) { // Verifica si la coroutine sigue activa (cooperativa)
                        Log.d("Coroutines_Job", "Cancelación detectada, saliendo...")
                        return@launch // Sale de la coroutine
                    }
                    Log.d("Coroutines_Job", "Procesando paso ${i + 1}...")
                    delay(500L) // Punto de suspensión que también verifica cancelación
                }
            } catch (e: CancellationException) {
                Log.d("Coroutines_Job", "Coroutine cancelada explícitamente: ${e.message}")
                // Limpieza si es necesario
                throw e // Re-lanzar es buena práctica si no se maneja completamente aquí
            } finally {
                Log.d("Coroutines_Job", "Bloque finally ejecutado (limpieza).")
            }
        }

        // Simular una cancelación después de un tiempo
        scope.launch {
            delay(1200L) // Esperar un poco
            if (job.isActive) {
                Log.d("Coroutines_Job", "Solicitando cancelación del job...")
                job.cancel(CancellationException("Operación cancelada por el usuario."))
            }
        }
    }

    val RX_TAG_EJEMPLO = "RxJavaBasico"

    fun demostrarFlujoRxJavaSimple() {
        // 1. Observable: Creamos una fuente que emite una secuencia de números enteros.
        val fuenteDeNumeros: Observable<Int> = Observable.just(1, 2, 3, 4, 5, 6)

        // Variable para mantener la referencia a la suscripción y poder cancelarla.
        var suscripcion: Disposable? = null

        Log.d(RX_TAG_EJEMPLO, "Iniciando la creación del flujo RxJava...")

        suscripcion = fuenteDeNumeros
            // 2. Operators: Aplicamos operadores para transformar y filtrar el flujo.
            .filter { numero -> numero % 2 == 0 } // Filtra solo los números pares.
            .map { numeroPar -> "Número Par Procesado: $numeroPar" } // Transforma cada número par a un String.
            .subscribe(
                { itemProcesado -> // onNext: Se invoca por cada ítem emitido por el Observable.
                    Log.d(RX_TAG_EJEMPLO, "onNext -> $itemProcesado")
                },
                { error -> // onError: Se invoca si ocurre algún error en el flujo.
                    Log.e(RX_TAG_EJEMPLO, "onError -> Error: ${error.message}")
                },
                { // onComplete: Se invoca cuando el Observable termina de emitir todos los ítems.
                    Log.d(RX_TAG_EJEMPLO, "onComplete -> Flujo completado.")
                }
            )
    }

}