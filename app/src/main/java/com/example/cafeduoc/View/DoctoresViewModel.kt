package com.example.cafeduoc.View

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.cafeduoc.Model.Doctor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class DoctoresViewModel(/* ... */) : ViewModel() {
    val error: Any = TODO()

    // _listaDoctores es privado y mutable, solo el ViewModel puede modificarlo.
    private val _listaDoctores = MutableStateFlow<List<Doctor>>(emptyList())

    // listaDoctores es público e inmutable (StateFlow), la UI solo puede leerlo/observarlo.
    val listaDoctores: StateFlow<List<Doctor>> = _listaDoctores

    fun cargarDoctores() {
        viewModelScope.launch {
            // Lógica para obtener los doctores del Modelo (ej. un Repositorio)
            // val doctoresDesdeRepo = doctorRepository.obtenerDoctores()
            // _listaDoctores.value = doctoresDesdeRepo
        }
    }

    fun cargarListaDoctores() {
        TODO("Not yet implemented")
    }
}




