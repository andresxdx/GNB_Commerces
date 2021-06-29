package com.andres.gnbcommerces.ui.view.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andres.gnbcommerces.R

class ProductsListAdapter(private val productCodes: MutableList<String>, private val listener: OnProductClickListener): RecyclerView.Adapter<ProductsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsListViewHolder(layoutInflater.inflate(R.layout.item_product  , parent, false), listener, productCodes)
    }

    override fun onBindViewHolder(productsHolder: ProductsListViewHolder, position: Int) {
        val productCode = productCodes[position]
        productsHolder.bind(productCode)
    }

    override fun getItemCount(): Int = productCodes.size

    interface OnProductClickListener {
        fun onProductClick(productCode: String)
    }

}