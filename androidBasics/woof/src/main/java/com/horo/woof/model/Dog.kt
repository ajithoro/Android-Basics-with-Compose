package com.horo.woof.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @DrawableRes val imageRes: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int,
)
