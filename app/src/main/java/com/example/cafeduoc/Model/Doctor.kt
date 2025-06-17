package com.example.cafeduoc.Model

// Modelo para representar a un doctor
data class Doctor(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val urlFoto: String
)