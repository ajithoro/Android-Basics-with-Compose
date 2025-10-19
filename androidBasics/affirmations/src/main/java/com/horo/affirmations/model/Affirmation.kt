package com.horo.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @DrawableRes val imgResourceId: Int,
    @StringRes val stringResId: Int
)
