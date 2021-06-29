package com.andres.gnbcommerces.ui.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andres.gnbcommerces.databinding.ProductsListFragmentBinding
import com.andres.gnbcommerces.ui.MainActivity
import com.andres.gnbcommerces.ui.view.transactions.ProductTransactionsFragment
import com.andres.gnbcommerces.ui.viewmodel.CommercesViewModel

class ProductsListFragment : Fragment(), ProductsListAdapter.OnProductClickListener, SearchView.OnQueryTextListener {

    private var _binding: ProductsListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CommercesViewModel by activityViewModels()

    private lateinit var adapter: ProductsListAdapter

    companion object {
        fun newInstance() = ProductsListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ProductsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        //binding.svProducts.setOnQueryTextListener(this)
        viewModel.onCreate()
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
        viewModel.productsLiveDataModel.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.progress.isVisible = it
        })
    }

    private fun initRecyclerView() {
        adapter = ProductsListAdapter(viewModel.productList, this)
        binding.rvProduct.setHasFixedSize(true)
        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProduct.adapter = adapter
    }

    override fun onProductClick(productCode: String) {
        viewModel.onProductSelected(productCode)
        if (activity is MainActivity) {
            (activity as MainActivity).navigateToFragment(ProductTransactionsFragment.newInstance(), true)
        }
    }

    /*private fun searchProductCode(query: String) {
        for (product in viewModel.productList) {
            if (product.equals(query, true)) {
                viewModel.productList.clear()
                viewModel.productList.add(product)
                adapter.notifyDataSetChanged()
                break
            }
        }
        if (activity is MainActivity) {
            (activity as MainActivity).hideKeyboard()
        }
    }*/

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            //searchProductCode(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun showError() {
        //TODO show error
    }

}