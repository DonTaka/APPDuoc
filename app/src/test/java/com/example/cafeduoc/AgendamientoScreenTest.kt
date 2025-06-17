package com.example.cafeduoc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class AgendamientoScreenTest {

    // 1. Regla de prueba para interactuar con Composable functions
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun agendarCita_cuandoSeLlenaMotivoYSePresionaBoton_muestraMensajeDeExito() {
        // --- 1. Arrange (Preparar) ---
        // Establecemos el contenido de la UI que vamos a probar.
        // En un caso real, aquí podrías tener que configurar mocks para el ViewModel.
        var mensajeConfirmacion by mutableStateOf("")
        composeTestRule.setContent {
            FormularioAgendamientoScreen(
                onCitaAgendada = { mensaje ->
                    mensajeConfirmacion = mensaje
                }
            )
        }

        // --- 2. Act (Actuar) ---
        // Encontrar el campo de texto por su testTag y escribir en él.
        composeTestRule.onNodeWithTag("MotivoTextField")
            .performTextInput("Chequeo anual para Bobby")

        // Encontrar el botón por su texto y hacer clic.
        composeTestRule.onNodeWithText("Agendar").performClick()

        // --- 3. Assert (Verificar) ---
        // Verificar que el mensaje de confirmación esperado ahora es visible en la UI.
        // Nota: En este ejemplo simple, verificamos la variable, pero en un caso real
        // buscaríamos el nodo de texto en la composición.
        composeTestRule.onNodeWithText("Cita para 'Chequeo anual para Bobby' agendada exitosamente.")
            .assertIsDisplayed()
        // assertEquals("Cita para 'Chequeo anual para Bobby' agendada exitosamente.", mensajeConfirmacion)
    }
}