package com.example.lab2.model.network

import com.example.lab2.model.entity.Celebrity
import retrofit2.Call
import retrofit2.http.GET


interface CelebrityService {

    @GET("v1/celebrity?name=all")
    fun fetchCelebrityList(): Call<List<Celebrity>>
}