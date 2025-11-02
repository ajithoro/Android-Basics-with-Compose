package com.horo.dessertclicker

import com.horo.dessertclicker.data.DataSource.dessertList

data class DessertUiState(
    val currentDessertIndex: Int = 0,
    val imageRes: Int = dessertList[currentDessertIndex].imageRes,
    val dessertSold: Int = 0,
    val currentPrice: Int = dessertList[currentDessertIndex].price,
    val revenue: Int = 0,
)
