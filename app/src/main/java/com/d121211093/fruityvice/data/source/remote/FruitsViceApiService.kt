package com.d121211093.fruityvice.data.source.remote

import com.d121211093.fruityvice.data.model.Fruits
import com.d121211093.fruityvice.data.model.FruitsItem
import retrofit2.http.GET

interface FruitsViceApiService {
    @GET("all")
    suspend fun getFruits() : List<FruitsItem>
}