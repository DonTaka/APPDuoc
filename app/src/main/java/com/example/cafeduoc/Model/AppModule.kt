package com.example.cafeduoc.Model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Las dependencias vivirán tanto como la app
object AppModule {

    @Provides
    @Singleton // Solo se creará una instancia de Retrofit en toda la app
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.clinicaveterinaria.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideClinicaApiService(retrofit: Retrofit): ClinicaApiService {
        return retrofit.create(ClinicaApiService::class.java)
    }
}