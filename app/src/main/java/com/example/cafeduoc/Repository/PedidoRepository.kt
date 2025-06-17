package com.example.cafeduoc.Repository

import com.example.cafeduoc.Model.Pedido
import com.example.cafeduoc.Model.Producto

object PedidoRepository {

    private var contador = 0
    private val pedidos = mutableListOf<Pedido>()

    fun agregarPedido(productos : List<Producto>):Pedido{
        val pedido = Pedido(contador++,productos)
        pedidos.add(pedido)
        return pedido
    }

    fun obtenerPedidos(): List<Pedido> = pedidos
}