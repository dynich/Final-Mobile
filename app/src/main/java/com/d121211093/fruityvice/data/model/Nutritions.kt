package com.d121211093.fruityvice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nutritions(
    @SerialName(value = "calories")
    val calories: Int,
    @SerialName(value = "carbohydrates")
    val carbohydrates: Double,
    @SerialName(value = "fat")
    val fat: Double,
    @SerialName(value = "protein")
    val protein: Double,
    @SerialName(value = "sugar")
    val sugar: Double
)