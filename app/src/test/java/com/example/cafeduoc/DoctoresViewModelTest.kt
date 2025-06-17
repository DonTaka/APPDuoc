package com.example.cafeduoc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cafeduoc.Model.Doctor
import com.example.cafeduoc.Repository.DoctorRepository
import com.example.cafeduoc.View.DoctoresViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException

@ExperimentalCoroutinesApi
class DoctoresViewModelTest {

    // Regla para que las tareas de arquitectura de componentes se ejecuten de forma síncrona
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Regla para manejar los dispatchers de coroutines en pruebas
    private val mainCoroutineRule = MainCoroutineRule() // MainCoroutineRule es una clase helper común

    // 1. Arrange (Preparar)
    private lateinit var doctorRepository: DoctorRepository // La dependencia
    private lateinit var viewModel: DoctoresViewModel // La unidad bajo prueba

    @Before
    fun setUp() {
        // Se ejecuta antes de cada prueba
        mainCoroutineRule.setUp() // Configura el dispatcher de prueba
        doctorRepository = mockk() // Crea el mock del repositorio
        viewModel = DoctoresViewModel(doctorRepository) // Inyecta el mock en el ViewModel
    }

    @After
    fun tearDown() {
        // Se ejecuta después de cada prueba para limpiar
        mainCoroutineRule.tearDown()
    }

    @Test
    fun cargarListaDoctores1(): Unit = mainCoroutineRule.runBlockingTest {
        // Arrange (continuación)
        val doctoresDePrueba = listOf(Doctor(1, "Dr. House", "Diagnóstico", ""))
        // Se define que cuando se llame a obtenerListaDoctores(), el mock devolverá nuestra lista de prueba.
        // Usamos coEvery para funciones suspend.
        coEvery { doctorRepository.obtenerListaDoctores() } returns doctoresDePrueba

        // 2. Act (Actuar)
        viewModel.cargarListaDoctores()

        // 3. Assert (Verificar)
        // Verificamos que el estado del ViewModel (ej. un StateFlow) ahora contiene la lista de prueba.
        assertEquals(doctoresDePrueba, viewModel.listaDoctores.value)
    }

    @Test
    fun cargarListaDoctores2(): Unit = mainCoroutineRule.runBlockingTest {
        // Arrange
        val excepcion = IOException("Error de red")
        // Definimos que el mock lance una excepción
        coEvery { doctorRepository.obtenerListaDoctores() } throws excepcion

        // Act
        viewModel.cargarListaDoctores()

        // Assert
        // Verificamos que el estado de error del ViewModel contiene el mensaje de la excepción.
        assertEquals("Error al cargar doctores: ${excepcion.message}", viewModel.error)
        // Verificamos que la lista de doctores está vacía.
        assertEquals(true, viewModel.listaDoctores.value.isEmpty())
    }
}

// Nota: MainCoroutineRule es una clase helper que se suele crear para manejar
// TestCoroutineDispatcher y setMain/resetMain, simplificando las pruebas con coroutines.