package com.d121211093.fruityvice.data

import com.d121211093.fruityvice.data.repository.FruitRepository
import com.d121211093.fruityvice.data.repository.NetworkFruitViceRepo
import com.d121211093.fruityvice.data.source.remote.FruitsViceApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface FruitViceContainer{
    val fruitRepository: FruitRepository
}

class DefaultAppContainer : FruitViceContainer{
    private val basUrl = "https://www.fruityvice.com/api/fruit/"
    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(basUrl)
        .build()

    private val retrofitService: FruitsViceApiService by lazy {
        retrofit.create(FruitsViceApiService:: class.java)
    }

    override val fruitRepository: FruitRepository by lazy {
        NetworkFruitViceRepo(retrofitService)
    }
}