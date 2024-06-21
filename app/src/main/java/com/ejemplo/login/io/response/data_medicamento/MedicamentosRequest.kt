package com.ejemplo.login.io.response.data_medicamento

data class MedicamentosRequest(
    val id: Int,
    val nombre: String,
    val formaFarmaceutica: String,
    val precioUnitario: Double,
    val stock: Int,
    val nombreCategoria: String

)