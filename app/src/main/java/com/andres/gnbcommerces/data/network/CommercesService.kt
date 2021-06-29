package com.andres.gnbcommerces.data.network

import com.andres.gnbcommerces.core.RetrofitHelper
import com.andres.gnbcommerces.data.model.CurrencyConversionModel
import com.andres.gnbcommerces.data.model.ProductTransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommercesService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCurrencyConversions(): List<CurrencyConversionModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CommercesApiClient::class.java).getAllCurrencyConversions()
            response.body() ?: emptyList()
        }
    }

    suspend fun getProductTransactions(): List<ProductTransactionModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CommercesApiClient::class.java).getAllProductTransactions()
            response.body() ?: emptyList()
        }
    }

}