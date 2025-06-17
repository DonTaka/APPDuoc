package com.example.cafeduoc.View

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cafeduoc.Model.Usuario
import com.example.cafeduoc.Repository.LoginRepository

class LoginViewModel:ViewModel() {

    val usuario = mutableStateOf("")
    val correo = mutableStateOf("")
    val contrasena = mutableStateOf("")

    val errorMessage = mutableStateOf("")
    val loginUsuario = mutableStateOf<Usuario?>(null)
    val registroUsuario = mutableStateOf<Usuario?>(null)

    fun login(){
        val usuarioInput = usuario.value
        val contrasenaInput = contrasena.value

        val user = LoginRepository.login(usuarioInput,contrasenaInput)

        if(user!=null){
            loginUsuario.value = user
            errorMessage.value = ""

        }else{
            errorMessage.value = "Usuario o contraseña incorrectos"
        }
    }

    fun register(){
        val usuarioInput = usuario.value.trim()
        val correoInput = correo.value.trim()
        val contrasenaInput = contrasena.value.trim()

        if(usuarioInput.isEmpty() || correoInput.isEmpty() || contrasenaInput.isEmpty()){
            errorMessage.value="Por favor. complete todos los campos"
        }else{

            val newUser = LoginRepository.register(usuarioInput,correoInput,contrasenaInput)
            if(newUser!=null){
                registroUsuario.value = newUser
                errorMessage.value = ""
            }else{
                errorMessage.value = "Usuario o correo ya existen"
            }
        }
    }
}