package com.example.cafeduoc.Model

 import androidx.room.Dao
 import androidx.room.Insert
 import androidx.room.Query
 import kotlinx.coroutines.flow.Flow // Si quieres observar cambios

 @Dao
 interface TareaDao {
     @Query("SELECT * FROM tareas ORDER BY fechaCreacion DESC")
     suspend fun obtenerTodasLasTareas(): List<Tarea> // Función de suspensión

     @Query("SELECT * FROM tareas ORDER BY fechaCreacion DESC")
     fun obtenerTodasLasTareasFlow(): Flow<List<Tarea>> // Alternativa reactiva con Flow

     @Insert
     suspend fun insertarTarea(tarea: Tarea)
 }

 data class Tarea(val id: Int, val descripcion: String, val fechaCreacion: Long) // Ejemplo de entidad
