package com.dagger.ui.main.presenterImpl

import com.dagger.models.Post
import com.dagger.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun loadData(list: List<Post>)
        fun progress(show: Boolean)
        fun onError(message: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun responsePost()
    }
}