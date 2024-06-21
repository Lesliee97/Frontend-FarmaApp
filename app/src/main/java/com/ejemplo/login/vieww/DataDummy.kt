package com.ejemplo.login.vieww

import android.content.Context
import android.widget.Toast
import com.ejemplo.login.R
import com.ejemplo.login.io.response.LoginRequest
import com.ejemplo.login.io.response.data_medicamento.MedicamentosResponse
import com.ejemplo.login.ui.MainActivity
import com.example.app.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataDummy {

    companion object {
        suspend fun getTopMenuProductos(context: Context): List<Productos> {
            return withContext(Dispatchers.IO) {
                try {
                    val response = RetrofitClient.getInstance(context).getAllMedicamentos()

                    if (response.size > 0) {
                        val medicamentosResponse = response
                        return@withContext medicamentosResponse.map { responseItem ->
                            Productos(
                                imagen = R.drawable.aspirina,
                                name = responseItem.nombre,
                                category = responseItem.formaFarmaceutica,
                                precio = responseItem.precioUnitario,
                                id = responseItem.id
                            )
                        }
                    } else {
                        System.out.println("Error al obtener los medicamentos: }")
                    }
                } catch (e: Exception) {
                    System.out.println("Error al obtener los medicamentos: $e")
                }

                return@withContext emptyList()
            }
        }
    }
}