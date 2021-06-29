package com.andres.gnbcommerces.data.network

import com.andres.gnbcommerces.data.model.CurrencyConversionModel
import com.andres.gnbcommerces.data.model.ProductTransactionModel
import retrofit2.Response
import retrofit2.http.GET

interface CommercesApiClient {

    @GET("rates.json")
    suspend fun getAllCurrencyConversions(): Response<List<CurrencyConversionModel>>

    @GET("transactions.json")
    suspend fun getAllProductTransactions(): Response<List<ProductTransactionModel>>

}