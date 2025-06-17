package com.example.cafeduoc.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeduoc.Model.ClinicaApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class MiViewModel @Inject constructor(private val apiService: ClinicaApiService) : ViewModel() {
     fun obtenerDatos() {
         viewModelScope.launch {
             try {
                 val datos = apiService.miEndpointSuspend()
                 // Procesar datos...
             } catch (e: Exception) {
                 // Manejar error...
             }
         }
     }
 }