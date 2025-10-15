package com.horo.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName("img_src")
    val imgSrc: String
)
