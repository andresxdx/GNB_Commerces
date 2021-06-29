package com.andres.gnbcommerces.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.gnbcommerces.data.model.CurrencyConversionModel
import com.andres.gnbcommerces.data.model.ProductTransactionModel
import com.andres.gnbcommerces.domain.GetCurrencyConversions
import com.andres.gnbcommerces.domain.GetProductsTransactions
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CommercesViewModel: ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    var getCurrencyConversions = GetCurrencyConversions()
    lateinit var currencyConversions: List<CurrencyConversionModel>
    var totalProductTransactionsOnEur = MutableLiveData<String>()

    var getProductTransactions = GetProductsTransactions()
    val productsLiveDataModel = MutableLiveData<MutableList<String>>()
    val productList = mutableListOf<String>()
    val selectedProduct = MutableLiveData<String>()

    lateinit var allTransactionsList: List<ProductTransactionModel>
    val transactionLiveDataModel = MutableLiveData<MutableList<ProductTransactionModel>>()
    val productTransactionsList = mutableListOf<ProductTransactionModel>()

    //TODO get currency conversions
    fun onCreate() {
        viewModelScope.launch {
            if (productList.isEmpty()) {
                isLoading.postValue(true)
                allTransactionsList = getProductTransactions()
                currencyConversions = getCurrencyConversions()
                if (!allTransactionsList.isNullOrEmpty()) {
                    //productList.clear()
                    for (product in allTransactionsList) {
                        if (!productList.contains(product.productCode)) {
                            productList.add(product.productCode)
                        }
                    }
                    productsLiveDataModel.postValue(productList)
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun onProductSelected(product: String) {
        viewModelScope.launch {
            selectedProduct.value = product
            productTransactionsList.clear()
            if (!allTransactionsList.isNullOrEmpty()) {
                //isLoading.postValue(true)
                var conversionOnEur = 0.0
                for (item in allTransactionsList) {
                    if (item.productCode == product) {
                        productTransactionsList.add(item)
                        conversionOnEur += getConversionOnEur(item.transactionAmount, item.transactionCurrency)
                    }
                }
                val precision = DecimalFormat("0.00")
                totalProductTransactionsOnEur.value = precision.format(conversionOnEur).toString() + " EUR"
                if (productTransactionsList.isNotEmpty()) {
                    transactionLiveDataModel.postValue(productTransactionsList)
                }
                //isLoading.postValue(false)
            }
        }
    }

    private fun getConversionOnEur(amount: String, currency: String): Double{
        val amountDouble = amount.toDouble()
        val conversion = if (currency.equals("eur", true)) {
            1.0
        } else {
            getCurrencyConversions(currency)
        }
        return amountDouble * conversion
    }

    private fun getCurrencyConversions(currency: String): Double {
        var found = false
        var rate = 0.0
        if (currency.isNotEmpty() && !currencyConversions.isNullOrEmpty()) {
            for (conversion in currencyConversions){
                if (conversion.fromCurrency == currency && conversion.toCurrency.equals("eur", true)) {
                    found = true
                    rate = conversion.rateCurrency.toDouble()
                } else if (conversion.fromCurrency.equals("eur", true) && conversion.toCurrency == (currency)) {
                    found = true
                    rate = 1 / conversion.rateCurrency.toDouble()
                }
            }
            /*if (!found) {
                var temporalCurrency: String
                for (conversion in currencyConversions){
                    if (conversion.toCurrency == currency){
                        rate = conversion.rateCurrency.toDouble()
                        temporalCurrency = conversion.fromCurrency
                    }

                }
            }*/

        }

        return rate
    }


}