package com.andres.gnbcommerces.ui.view.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andres.gnbcommerces.R
import com.andres.gnbcommerces.data.model.ProductTransactionModel

class ProductTransactionsAdapter(private val productTransactions: MutableList<ProductTransactionModel>): RecyclerView.Adapter<ProductTransactionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTransactionsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductTransactionsViewHolder(layoutInflater.inflate(R.layout.item_transaction  , parent, false))
    }

    override fun onBindViewHolder(transactionsHolder: ProductTransactionsViewHolder, position: Int) {
        val productTransaction = productTransactions[position]
        transactionsHolder.bind(productTransaction)
    }

    override fun getItemCount(): Int = productTransactions.size

}