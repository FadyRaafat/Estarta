package com.fady.estarta.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fady.estarta.R
import com.fady.estarta.databinding.FragmentSearchBinding
import com.fady.estarta.presentation.viewmodels.StartViewModel
import com.fady.estarta.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : BaseFragment<FragmentSearchBinding, StartViewModel>() {

    override
    fun layout(): Int = R.layout.fragment_search

    override
    val viewModel: StartViewModel
            by viewModels()

    override
    fun setupObservers() {
        viewModel.proceedToHomeScreenFlow.observe(viewLifecycleOwner) {
            if (it.first)
                toHomeScreen(it.second)
        }
    }

    override
    fun FragmentSearchBinding.initializeUI() {
        binding.apply {
            searchView.setOnQueryTextListener(
                object : android.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.checkQuery(query)
                        return false
                    }
                })

        }
    }

    private fun toHomeScreen(query : String) {
        findNavController().navigate(
            FragmentSearchDirections.actionFragmentSearchToFragmentHome(query)
        )
    }


}