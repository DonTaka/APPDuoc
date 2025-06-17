package com.example.cafeduoc.Model

data class Pedido (
    val id:Int,
    val productos:List<Producto>
){
    val total:Double
        get()=productos.sumOf{it.precio}
}