package com.example.cafeduoc.Model



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// Interfaz que define los endpoints de nuestra API
import retrofit2.http.GET

interface ClinicaApiService {
    @GET("veterinarios") // Endpoint para obtener la lista de doctores
    suspend fun obtenerDoctores(): List<Doctor> // La llamada se define como suspend fun
}
object RetrofitClient {
    private const val BASE_URL = "https://api.clinicaveterinaria.com/"

    val instancia: ClinicaApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ClinicaApiService::class.java)
    }
}