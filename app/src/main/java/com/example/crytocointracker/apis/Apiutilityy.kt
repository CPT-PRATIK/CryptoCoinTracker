package com.example.crytocointracker.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apiutilityy {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}