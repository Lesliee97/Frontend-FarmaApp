package com.ejemplo.login.io.response.data_medicamento

data class MedicamentosResponse(
    val id: Int,
    val nombre: String,
    val formaFarmaceutica: String,
    val precioUnitario: Float,
    val stock: Int,
    val nombreCategoria: String

)