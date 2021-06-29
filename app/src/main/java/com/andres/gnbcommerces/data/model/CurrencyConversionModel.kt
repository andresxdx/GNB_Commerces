package com.andres.gnbcommerces.data.model

import com.google.gson.annotations.SerializedName

class CurrencyConversionModel(
    @SerializedName("from") val fromCurrency: String,
    @SerializedName("to") val toCurrency: String,
    @SerializedName("rate") val rateCurrency: String
)