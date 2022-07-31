package com.dagger.ui

class BaseContract {
    interface Presenter<in T> {
        fun subscribe()
        fun unSubscribe()
        fun attachView(view: T)
    }

    interface View {}
}