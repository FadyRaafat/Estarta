package com.fady.estarta.presentation.viewmodels

import com.fady.estarta.utils.base.BaseViewModel
import com.fady.estarta.utils.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor() : BaseViewModel() {

    private val proceedToHomeScreen = SingleLiveEvent<Pair<Boolean, String>>()
    val proceedToHomeScreenFlow = proceedToHomeScreen


    fun checkQuery(query: String?) {
        if (query.isNullOrEmpty().not()) {
            proceedToHomeScreen(query ?: "")
        }
    }

    private fun proceedToHomeScreen(query: String) {
        proceedToHomeScreen.postValue(Pair(true, query))
    }


}