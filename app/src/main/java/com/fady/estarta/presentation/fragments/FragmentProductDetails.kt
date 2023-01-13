package com.fady.estarta.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fady.estarta.R
import com.fady.estarta.data.models.Result
import com.fady.estarta.databinding.FragmentProductDetailsBinding
import com.fady.estarta.presentation.viewmodels.ProductViewModel
import com.fady.estarta.utils.base.BaseFragment
import com.fady.estarta.utils.common.BindingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentProductDetails : BaseFragment<FragmentProductDetailsBinding, ProductViewModel>() {

    private var productSelected: Result? = null

    override
    fun layout(): Int = R.layout.fragment_product_details

    override
    val viewModel: ProductViewModel
            by viewModels()

    override
    fun getFragmentArguments() {
        val args: FragmentProductDetailsArgs by navArgs()
        productSelected = args.product
    }

    override
    fun FragmentProductDetailsBinding.initializeUI() {
        binding.apply {
            toolbar.toolbarTitle.text = productSelected?.name
            toolbar.backBtn.setOnClickListener { findNavController().popBackStack() }
            BindingAdapter.setNetworkImage(
                headerImageTV,
                productSelected?.imageUrls?.get(0)
            )
            productNameTV.text = productSelected?.name
            productPriceTV.text = productSelected?.price
        }
    }


}
