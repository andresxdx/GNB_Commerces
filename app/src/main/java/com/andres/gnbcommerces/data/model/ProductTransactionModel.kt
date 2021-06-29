package com.andres.gnbcommerces.data.model

import com.google.gson.annotations.SerializedName

class ProductTransactionModel(
    @SerializedName("sku") val productCode: String,
    @SerializedName("amount") val transactionAmount: String,
    @SerializedName("currency") val transactionCurrency: String
)