package com.fady.estarta.utils.common

class NetworkState(var status: Status?, var msg: String?) {

    enum class Status {
        INIT_RUNNING,
        RUNNING,
        SUCCESS,
        FAILED
    }

    companion object {

        var LOADED = NetworkState(Status.SUCCESS, null)
        var LOADING = NetworkState(Status.RUNNING, null)
        var INIT_LOADING = NetworkState(Status.INIT_RUNNING, null)

        fun ERROR(msg: String): NetworkState {
            return NetworkState(Status.FAILED, msg)
        }
    }
}
