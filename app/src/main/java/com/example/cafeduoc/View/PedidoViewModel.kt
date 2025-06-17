package com.example.cafeduoc.View

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.cafeduoc.Model.Pedido
import com.example.cafeduoc.Model.Producto
import com.example.cafeduoc.Repository.PedidoRepository
import com.example.cafeduoc.Repository.ProductoRepository

class PedidoViewModel: ViewModel() {
    val productos: List<Producto> = ProductoRepository.obtenerProductos()

    val carrito = mutableStateListOf<Producto>()

    val pedidos  = mutableStateListOf<Pedido>()

    fun agregarAlCarrito(producto:Producto){
        carrito.add(producto)
    }
    fun removerDelCarrito(producto:Producto){
        carrito.remove(producto)
    }

    fun realizarPedido(){
        if(carrito.isNotEmpty()){
            val pedido = PedidoRepository.agregarPedido(carrito.toList())
            pedidos.add(pedido)
            carrito.clear()
        }
    }

}