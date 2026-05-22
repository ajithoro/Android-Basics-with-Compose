package com.horo.lunchtray.extensions

import java.text.NumberFormat

fun Double.toFormattedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}
