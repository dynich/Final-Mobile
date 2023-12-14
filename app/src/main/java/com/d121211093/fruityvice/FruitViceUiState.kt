package com.d121211093.fruityvice

import com.d121211093.fruityvice.data.model.Fruits
import com.d121211093.fruityvice.data.model.FruitsItem

sealed interface FruitViceMainState{
    data class Success(val fruits: List<FruitsItem>):FruitViceMainState
    object Failure:FruitViceMainState
    object Loading: FruitViceMainState
}
sealed interface FruitViceDetailsState{
    data class Success(val fruits: FruitsItem):FruitViceDetailsState
    object Failure:FruitViceDetailsState
    object Loading: FruitViceDetailsState
}

data class FruitViceUiState(
    val mainScreenState: FruitViceMainState = FruitViceMainState.Loading,
    val detailScreenState: FruitViceDetailsState = FruitViceDetailsState.Loading,
    val test:String = "Penambilan Data"
)
