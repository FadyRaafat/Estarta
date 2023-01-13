package com.fady.estarta.utils.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.fady.estarta.utils.common.AppExecutors
import utils.common.handleApiError
import utils.common.hideLoadingDialog
import utils.common.showLoadingDialog

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel?> : Fragment() {

    lateinit var binding: V

    lateinit var appExecutors: AppExecutors
    protected abstract val viewModel: VM
    private var progressDialog: Dialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appExecutors = AppExecutors()
        binding = DataBindingUtil.inflate(inflater, layout(), container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFragmentArguments()
        binding.initializeUI()
        setupObservers()
        setUpBaseObservers()
    }

    private fun setUpBaseObservers() {
        viewModel?.showLoading?.observe(viewLifecycleOwner) { showLoading ->
            if (showLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        viewModel?.showApiError?.observe(viewLifecycleOwner) { failureResource ->
            handleApiError(failureResource)
        }
    }

    private fun showLoading() {
        hideLoading()
        progressDialog = showLoadingDialog(requireActivity(), null)
    }

    fun showLoading(hint: String?) {
        hideLoading()
        progressDialog = showLoadingDialog(requireActivity(), hint)
    }

    private fun hideLoading() = hideLoadingDialog(progressDialog, requireActivity())

    override
    fun onResume() {
        super.onResume()
        registerListeners()
    }

    override
    fun onPause() {
        unRegisterListeners()

        super.onPause()
    }

    open fun registerListeners() {}

    open fun unRegisterListeners() {}

    open fun setupObservers() {}

    open fun getFragmentArguments() {}

    open fun V.initializeUI() {}

    abstract fun layout(): Int

}