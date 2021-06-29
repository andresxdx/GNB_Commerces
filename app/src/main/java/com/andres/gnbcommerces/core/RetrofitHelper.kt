package com.andres.gnbcommerces.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://quiet-stone-2094.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}