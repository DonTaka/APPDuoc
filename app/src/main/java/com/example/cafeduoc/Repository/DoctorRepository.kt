package com.example.cafeduoc.Repository

import androidx.lifecycle.ViewModel
import com.example.cafeduoc.Model.ClinicaApiService
import com.example.cafeduoc.Model.Doctor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Definimos la abstracción (la interfaz)
interface DoctorRepository {
    suspend fun obtenerListaDoctores(): List<Doctor>
}

// La implementación concreta depende de la fuente de datos
class DoctorRepositoryImpl(private val apiService: ClinicaApiService) : DoctorRepository {
    override suspend fun obtenerListaDoctores(): List<Doctor> {
        return apiService.obtenerDoctores()
    }
}

// El ViewModel depende de la abstracción, no de la implementación
@HiltViewModel
class DoctoresViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository // ¡Depende de la interfaz!
) : ViewModel() {
    // ...
}