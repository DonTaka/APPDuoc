package com.example.cafeduoc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cafeduoc.View.ListaDoctoresScreen

import kotlinx.coroutines.delay
import com.example.cafeduoc.ui.theme.CafeDuocTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CafeDuocTheme {
                Surface {
                    SimpleThreadingScreen()

                }

            }
        }
    }

    val TAG_SIMPLE_THREAD = "SimpleThreadDemo"

    @Composable
    fun SimpleThreadingScreen() {
        var statusText by remember { mutableStateOf("Presiona un botón para iniciar.") }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = statusText,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Botón 1: Ejecuta una tarea bloqueante en el Hilo Principal
            Button(
                onClick = {
                    Log.d(TAG_SIMPLE_THREAD, "Botón 'Bloqueante': Tarea iniciándose en UI Thread.")
                    statusText =
                        "Procesando en UI Thread (bloqueante)..." // Se verá antes del bloqueo

                    // Simulación de una tarea muy larga (5 segundos)
                    try {
                        Thread.sleep(5000) // ¡ESTO BLOQUEA EL HILO PRINCIPAL!
                        statusText = "Tarea bloqueante completada en UI Thread."
                        Log.d(TAG_SIMPLE_THREAD, "Botón 'Bloqueante': Tarea finalizada.")
                    } catch (e: InterruptedException) {
                        statusText = "Tarea bloqueante interrumpida."
                        Log.e(TAG_SIMPLE_THREAD, "Botón 'Bloqueante': Tarea interrumpida.", e)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ejecutar Tarea Bloqueante en UI Thread")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón 2: Ejecuta la misma tarea en un Hilo Secundario
            Button(
                onClick = {
                    Log.d(
                        TAG_SIMPLE_THREAD,
                        "Botón 'No Bloqueante': Iniciando tarea en hilo secundario."
                    )
                    statusText =
                        "Iniciando tarea en hilo secundario..." // Actualización inmediata de la UI

                    // Creamos e iniciamos un nuevo hilo para la tarea larga
                    Thread {
                        try {
                            Log.d(TAG_SIMPLE_THREAD, "Hilo Secundario: Tarea ejecutándose...")
                            // Simulación de la misma tarea larga (5 segundos)
                            Thread.sleep(5000) // Se ejecuta en este hilo secundario, NO bloquea el UI Thread.
                            val resultado = "Tarea en hilo secundario completada."

                            // En Jetpack Compose, las escrituras en MutableState son thread-safe
                            // y programan una recomposición de forma segura.
                            statusText = resultado
                            Log.d(
                                TAG_SIMPLE_THREAD,
                                "Hilo Secundario: Estado actualizado ($resultado)."
                            )

                        } catch (e: InterruptedException) {
                            // Aunque no interrumpimos activamente este hilo en este ejemplo simple,
                            // es buena práctica manejar la InterruptedException.
                            statusText = "Tarea en hilo secundario fue interrumpida."
                            Log.e(TAG_SIMPLE_THREAD, "Hilo Secundario: Tarea interrumpida.", e)
                        }
                    }.start() // No olvidar iniciar el hilo.
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ejecutar Tarea en Hilo Secundario")
            }
        }
    }


    // Esta es una función de suspensión que simula una tarea que toma tiempo.
    suspend fun obtenerDatosDelServidor(): String {
        Log.d("Coroutines", "Solicitando datos al servidor...")
        delay(2000L) // Pausa la coroutine por 2 segundos (no bloquea el hilo)
        Log.d("Coroutines", "Datos recibidos.")
        return "Respuesta del Servidor"
    }

    suspend fun realizarOperacionEnRed(): String {
        delay(1500L) // Simula una operación de red
        return "Datos obtenidos de la red"
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun FotoDoctor(urlImagen: String, nombreDoctor: String) {
        GlideImage(
            model = urlImagen,
            contentDescription = "Foto de perfil de $nombreDoctor",
            // Muestra un placeholder de tu app mientras la imagen carga
            loading = placeholder(R.drawable.ic_placeholder_doctor),
            // Muestra una imagen de error si la carga falla
            failure = placeholder(R.drawable.ic_error_doctor),
            contentScale = ContentScale.Crop, // Escala la imagen para que cubra el espacio
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape) // Aplica un recorte circular
        )
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "lista_doctores") {
            composable(route = "lista_doctores") {
                ListaDoctoresScreen(
                    onDoctorClick = { IdDoctor ->
                        navController.navigate("detalle_doctor/$IdDoctor")
                    }
                )
            }
            composable(route = "detalle_doctor/{doctorId}") { backStackEntry ->
                val doctorId = backStackEntry.arguments?.getString("doctorId")
                DetalleDoctorScreen(doctorId = doctorId)
            }
            // ... otras pantallas (destinos)
        }
    }

    // En nuestro Composable del formulario de agendamiento
    @Composable
    fun FormularioAgendamientoScreen(onCitaAgendada: (String) -> Unit) {
        var motivo by remember { mutableStateOf("") }

        Column {
            TextField(
                value = motivo,
                onValueChange = { motivo = it },
                label = { Text("Motivo de la consulta") },
                // Añadimos un testTag para encontrarlo en la prueba
                modifier = Modifier.testTag("MotivoTextField")
            )
            Button(
                onClick = {
                    // Lógica para agendar...
                    onCitaAgendada("Cita para '$motivo' agendada exitosamente.")
                }
            ) {
                Text("Agendar")
            }
        }
    }
}



