package com.example.cafeduoc.Repository

import com.example.cafeduoc.Model.Producto

object  ProductoRepository {
    private val productos = listOf(
        Producto(1,"Cafe","Cafe recien hecho , sabor intenso",2500.0),
        Producto(2,"Te","Te natural sabor menta",2000.0),
        Producto(3,"Sandwich","Sandwich Aliado",3000.0)
    )

    fun obtenerProductos():List<Producto> =productos
}