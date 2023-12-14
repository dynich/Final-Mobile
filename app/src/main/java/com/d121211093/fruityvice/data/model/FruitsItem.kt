package com.d121211093.fruityvice.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FruitsItem(
    @SerialName(value = "family")
    val family: String,
    @SerialName(value = "genus")
    val genus: String,
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "nutritions")
    val nutritions: Nutritions,
    @SerialName(value = "order")
    val order: String
)