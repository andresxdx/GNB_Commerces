package com.andres.gnbcommerces.data

import com.andres.gnbcommerces.data.model.CommercesProvider
import com.andres.gnbcommerces.data.model.CurrencyConversionModel
import com.andres.gnbcommerces.data.model.ProductTransactionModel
import com.andres.gnbcommerces.data.network.CommercesService

class CommercesRepository {

    private val api = CommercesService()

    suspend fun getAllCurrencyConversions(): List<CurrencyConversionModel> {
        val response = api.getCurrencyConversions()
        CommercesProvider.currencyConversions = response
        return response
    }

    suspend fun getAllProductTransactions(): List<ProductTransactionModel> {
        val response = api.getProductTransactions()
        CommercesProvider.productTransactions = response
        return response
    }

}