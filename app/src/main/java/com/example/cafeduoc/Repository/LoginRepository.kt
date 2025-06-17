package com.example.cafeduoc.Repository

import com.example.cafeduoc.Model.Usuario

object LoginRepository {

    private val usuarios = mutableListOf(
        Usuario(1,"admin","admin","admin"),
        Usuario(2,"user","user","user")
    )

    fun login(usuario:String,contrasena:String):Usuario?{
        return usuarios.find{it.usuario==usuario && it.contrasena==contrasena}
    }

    fun register(usuario:String,correo:String,contrasena:String):Usuario?{

        if(usuarios.any{it.usuario==usuario || it.correo==correo}){
            return null
        }

        val newId = (usuarios.maxOfOrNull{it.id}?:0) + 1

        val newUser= Usuario(newId,usuario,correo,contrasena)
        usuarios.add(newUser)
        return newUser
    }

}