package com.andres.gnbcommerces.ui.view.transactions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andres.gnbcommerces.data.model.ProductTransactionModel
import com.andres.gnbcommerces.databinding.ItemTransactionBinding

class ProductTransactionsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemTransactionBinding.bind(view)

    fun bind(productTransaction: ProductTransactionModel) {
        binding.tvAmount.text = productTransaction.transactionAmount
        binding.tvCurrency.text = productTransaction.transactionCurrency
    }

}