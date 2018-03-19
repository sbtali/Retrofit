package com.alisabet.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {
    @GET("?json=get_category_index")
    fun getCategoryDetails(): Call<CategoryResponse>

    companion object Factory {
        val BASE_URL = "https://androidteachers.com/"
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}