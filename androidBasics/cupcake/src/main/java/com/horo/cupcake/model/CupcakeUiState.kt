package com.horo.cupcake.model

data class CupcakeUiState(
    val quantity: Int = 0,
    val flavor: String = "",
    val pickUpDate: String = "",
    val subTotal: String = "",
    val pickUpOptionList: List<String> = listOf(),
)
