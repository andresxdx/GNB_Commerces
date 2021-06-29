package com.andres.gnbcommerces.ui.view.products

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andres.gnbcommerces.databinding.ItemProductBinding

class ProductsListViewHolder(view: View, private val listener: ProductsListAdapter.OnProductClickListener, private val productCodes: MutableList<String>): RecyclerView.ViewHolder(view), View.OnClickListener {

    private val binding = ItemProductBinding.bind(view)

    init {
        view.setOnClickListener(this)
    }

    fun bind(productCode: String) {
        binding.tvProduct.text = productCode
    }

    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION){
            listener.onProductClick(productCodes[position])
        }
    }

}