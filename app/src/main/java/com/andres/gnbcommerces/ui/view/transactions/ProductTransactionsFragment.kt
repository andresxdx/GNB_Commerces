package com.andres.gnbcommerces.ui.view.transactions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andres.gnbcommerces.data.model.ProductTransactionModel
import com.andres.gnbcommerces.databinding.ProductTransactionsFragmentBinding
import com.andres.gnbcommerces.ui.view.products.ProductsListAdapter
import com.andres.gnbcommerces.ui.viewmodel.CommercesViewModel

class ProductTransactionsFragment : Fragment() {

    private var _binding: ProductTransactionsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CommercesViewModel by activityViewModels()

    private lateinit var adapter: ProductTransactionsAdapter

    companion object {
        fun newInstance() = ProductTransactionsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ProductTransactionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI() {
        initSubscription()
        initRecyclerView()
    }

    private fun initSubscription() {
        viewModel.transactionLiveDataModel.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })

        viewModel.selectedProduct.observe(viewLifecycleOwner, { product ->
            binding.tvProductCode.text = product
        })
        viewModel.totalProductTransactionsOnEur.observe(viewLifecycleOwner, {
            binding.tvTotalEur.text = it
        })

    }

    private fun initRecyclerView() {
        adapter = ProductTransactionsAdapter(viewModel.productTransactionsList)
        binding.rvTransaction.setHasFixedSize(true)
        binding.rvTransaction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransaction.adapter = adapter
    }

}