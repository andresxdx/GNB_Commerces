package com.andres.gnbcommerces.domain

import com.andres.gnbcommerces.data.CommercesRepository
import com.andres.gnbcommerces.data.model.ProductTransactionModel

class GetProductsTransactions {

    private val repository = CommercesRepository()

    suspend operator fun invoke(): List<ProductTransactionModel> = repository.getAllProductTransactions()

}