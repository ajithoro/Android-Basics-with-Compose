package com.horo.lunchtray.model

data class OrderSummary(
    val entreeItem: MenuItem.EntreeItem? = null,
    val sideDishItem: MenuItem.SideDishItem? = null,
    val accompanimentItem: MenuItem.AccompanimentItem? = null,
    val subtotal: Double = 0.0,
    val tax: Double = 0.0,
    val total: Double = 0.0,
)
