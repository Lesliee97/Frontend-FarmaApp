package com.ejemplo.login.io.response.data

data class RegisterRequest(
    val username: String,
    val nombres: String,
    val apellidos: String,
    val historia: String,
    val celular: String,
    val password: String,
    val correo: String
)
