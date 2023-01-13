package com.fady.estarta.utils.base

import androidx.lifecycle.ViewModel
import com.fady.estarta.utils.common.Resource
import com.fady.estarta.utils.common.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    var showLoading = SingleLiveEvent<Boolean>()
    var showApiError = SingleLiveEvent<Resource.Failure>()
}