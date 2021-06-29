package com.andres.gnbcommerces.domain

import com.andres.gnbcommerces.data.CommercesRepository
import com.andres.gnbcommerces.data.model.CurrencyConversionModel

class GetCurrencyConversions {

    private val repository = CommercesRepository()

    suspend operator fun invoke(): List<CurrencyConversionModel> = repository.getAllCurrencyConversions()

}