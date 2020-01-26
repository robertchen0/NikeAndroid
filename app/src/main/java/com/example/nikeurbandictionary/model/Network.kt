package com.example.nikeurbandictionary.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network() {

    fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
        .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

    fun getApi(): NetworkApi{
        return initRetrofit().create(NetworkApi::class.java)
    }
    companion object{
        private var instance: Network = Network()
        fun getInstance(): Network{
            return instance
        }
    }
}