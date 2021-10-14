package com.example.otroretrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDogsResponse(@Url url:String):Response<DogResponse>
}