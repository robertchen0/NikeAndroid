package com.example.nikeurbandictionary.model

import com.example.nikeurbandictionary.model.classes.Definition
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkApi {


    // "https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=hello"

    @GET("define")
    @Headers(
        "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
        "x-rapidapi-key: 12b8c4fae6msh15042aff270401ap1efa0djsn650a7fedf7cb")
    fun searchWord(@Query("term") word: String) : Call<Definition>
}