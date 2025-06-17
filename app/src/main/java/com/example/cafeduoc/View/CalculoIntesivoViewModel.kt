package com.example.cafeduoc.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.isActive // Para cancelación cooperativa
import kotlinx.coroutines.ensureActive // Otra forma de verificar cancelación
import android.util.Log
import kotlin.coroutines.coroutineContext


class CalculoIntensivoViewModel : ViewModel() {
    private val _resultadoProceso = MutableStateFlow<Long?>(null)
    val resultadoProceso: StateFlow<Long?> = _resultadoProceso

    private val _estadoActual = MutableStateFlow<String>("Listo para calcular")
    val estadoActual: StateFlow<String> = _estadoActual

    fun ejecutarCalculo(valorEntrada: Int) {
        viewModelScope.launch { // Coroutine principal en viewModelScope (usualmente Main)
            Log.d(CPU_TAG, "ViewModel: Solicitud de cálculo recibida.")
            _estadoActual.value = "Procesando cálculo intensivo..."
            _resultadoProceso.value = null // Limpiar resultado anterior

            try {
                // Cambiamos al Dispatcher.Default para la tarea intensiva en CPU
                val resultado = withContext(Dispatchers.Default) {
                    simularCalculoPesado(valorEntrada)
                }
                _resultadoProceso.value = resultado
                _estadoActual.value = "Cálculo completado."
                Log.d(CPU_TAG, "ViewModel: Cálculo finalizado con resultado: $resultado")
            } catch (e: kotlinx.coroutines.CancellationException) {
                _estadoActual.value = "Cálculo cancelado."
                Log.d(CPU_TAG, "ViewModel: El cálculo fue cancelado.")
            } catch (e: Exception) {
                _estadoActual.value = "Error durante el cálculo: ${e.localizedMessage}"
                Log.e(CPU_TAG, "ViewModel: Error en el cálculo", e)
            }
        }
    }
}


const val CPU_TAG = "CpuIntensiveTask"
suspend fun simularCalculoPesado(parametro: Int): Long {
    // No es estrictamente 'suspend' por sí misma si no llama a otras suspend fun,
    // pero se llamará desde un contexto de coroutine.
    Log.d(CPU_TAG, "Iniciando cálculo pesado en hilo: ${Thread.currentThread().name}")
    var resultado: Long = 0
    // Simulación de un bucle muy largo que consume CPU
    for (i in 0 until parametro * 200_000_000) { // Ajusta el multiplicador según la potencia de prueba
         if (!coroutineContext.isActive) { // Verificación manual para cancelación cooperativa
             Log.d(CPU_TAG, "Cálculo cancelado.")
             throw kotlinx.coroutines.CancellationException("Cálculo cancelado por el usuario")
         }
         coroutineContext.ensureActive()
        resultado += (i.toLong() % 1000) - (i.toLong() % 500) // Operación simple pero repetitiva
    }
    Log.d(CPU_TAG, "Cálculo pesado finalizado.")
    return resultado
}