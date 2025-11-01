package com.horo.dessertclicker

import androidx.annotation.DrawableRes
import com.horo.dessertclicker.data.DataSource.dessertList

data class DessertUiState(
    val currentDessertIndex: Int = 0,
    @DrawableRes val imageRes: Int = dessertList[currentDessertIndex].imageRes,
    var dessertSold: Int = 0,
    var currentPrice: Int = dessertList[currentDessertIndex].price,
    val revenue: Int = 0
)
