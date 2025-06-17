package com.example.cafeduoc.View

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeduoc.Model.Tarea
import com.example.cafeduoc.Model.TareaDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException

class TareasViewModel(private val tareaDao: TareaDao) : ViewModel() {


    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    fun cargarTareas() {
        viewModelScope.launch { // Se ejecuta en el dispatcher principal por defecto (Main.immediate)
            Log.d("RoomDemo", "Cargando tareas desde ViewModel...")
            try {
                // Cambiamos a Dispatchers.IO para la operación de base de datos
                val listaTareas = withContext(Dispatchers.IO) {
                    tareaDao.obtenerTodasLasTareas()
                }
                _tareas.value = listaTareas
                Log.d("RoomDemo", "Tareas cargadas: ${listaTareas.size}")
            } catch (e: Exception) {
                Log.e("RoomDemo", "Error al cargar tareas", e)
            }
        }
    }

    // Importante para el manejo de excepciones
    val HTTP_TAG = "HttpURLConnectionDemo"

    suspend fun realizarLlamadaHttpDirecta(urlString: String): String {
        // Esta función se ejecutará en un hilo de IO gracias a withContext en el ViewModel
        Log.d(HTTP_TAG, "Iniciando conexión a: $urlString")
        val url = URL(urlString)
        var urlConnection: HttpURLConnection? = null
        val stringBuilder = StringBuilder()

        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET" // Especificar método GET
            urlConnection.connectTimeout = 15000 // Timeout de conexión 15 segundos
            urlConnection.readTimeout = 10000    // Timeout de lectura 10 segundos

            val responseCode = urlConnection.responseCode
            Log.d(HTTP_TAG, "Código de respuesta: $responseCode")

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = urlConnection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
                reader.close()
                Log.d(HTTP_TAG, "Respuesta recibida exitosamente.")
            } else {
                Log.e(HTTP_TAG, "Error en la respuesta del servidor. Código: $responseCode")
                throw IOException("Error en la respuesta del servidor: $responseCode ${urlConnection.responseMessage}")
            }
        } catch (e: Exception) {
            Log.e(HTTP_TAG, "Excepción durante la llamada HTTP: ${e.message}", e)
            throw e // Re-lanzar para que el ViewModel la maneje
        } finally {
            urlConnection?.disconnect()
            Log.d(HTTP_TAG, "Conexión cerrada.")
        }
        return stringBuilder.toString()
    }
}
