package com.horo.dessertclicker.model

import androidx.annotation.DrawableRes

data class Dessert(@DrawableRes val imageRes: Int, val price: Int, val startAmountProduction: Int)
