package com.fady.estarta.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fady.estarta.R
import com.fady.estarta.data.models.Result
import com.fady.estarta.databinding.FragmentHomeBinding
import com.fady.estarta.presentation.adapters.ProductsAdapter
import com.fady.estarta.presentation.viewmodels.ProductViewModel
import com.fady.estarta.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : BaseFragment<FragmentHomeBinding, ProductViewModel>() {

    private var queryString: String? = null

    override
    fun layout(): Int = R.layout.fragment_home

    override
    val viewModel: ProductViewModel
            by viewModels()

    override
    fun getFragmentArguments() {
        val args: FragmentHomeArgs by navArgs()
        queryString = args.query
    }

    override
    fun FragmentHomeBinding.initializeUI() {
        binding.apply {
            toolbar.toolbarTitle.text = queryString
            toolbar.backBtn.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override
    fun registerListeners() {
        if (viewModel.getProductsResponse() == null) {
            viewModel.getProducts()
        } else {
            setupProductsRV()
        }
    }

    override
    fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getProductsSuccess.collect { apiSuccess ->
                if (apiSuccess) {
                    setupProductsRV()
                }
            }
        }


    }

    private fun setupProductsRV() {
        val productsAdapter = ProductsAdapter {
            toProductDetails(it)
        }
        binding.productsRv.apply {
            adapter = productsAdapter
            productsAdapter.submitList(viewModel.getProductsResponse()?.results)
        }
    }

    private fun toProductDetails(selectedProduct: Result) {
        findNavController().navigate(
            FragmentHomeDirections.actionFragmentHomeToFragmentProductDetails(
                selectedProduct
            )
        )

    }
}