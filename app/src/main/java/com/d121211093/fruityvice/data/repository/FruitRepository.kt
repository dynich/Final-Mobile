package com.d121211093.fruityvice.data.repository

import com.d121211093.fruityvice.data.model.Fruits
import com.d121211093.fruityvice.data.model.FruitsItem
import com.d121211093.fruityvice.data.source.remote.FruitsViceApiService

interface FruitRepository{
    suspend fun getFruits() : List<FruitsItem>
}

class NetworkFruitViceRepo(private  val fruitsViceApiService: FruitsViceApiService) : FruitRepository{
    override suspend fun getFruits(): List<FruitsItem> = fruitsViceApiService.getFruits()
}