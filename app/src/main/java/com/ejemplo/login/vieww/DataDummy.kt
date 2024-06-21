package com.ejemplo.login.vieww

import com.ejemplo.login.R

class DataDummy{
    companion object{
        fun getTopMenuProductos():List<Productos>{
            return listOf(
                Productos(
                    imagen = R.drawable.aspirina,
                    name = "Aspirina",
                    category = "Pastilla",
                    precio = 6.99f
                ),
                Productos(
                    imagen = R.drawable.eterogermina,
                    name = "Eterogermina",
                    category = "Frasco",
                    precio = 5.99f
                ),
                Productos(
                    imagen = R.drawable.panadol,
                    name = "Panadol",
                    category = "Pastilla",
                    precio = 2.50f
                ),
                Productos(
                    imagen = R.drawable.vaporub,
                    name = "Vaporud",
                    category = "Crema",
                    precio = 7.99f
                )
            )
        }
    }
}