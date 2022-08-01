package com.dagger.ui.main.presenterImpl

import com.dagger.models.DetailsViewModel
import com.dagger.models.Post
import com.dagger.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun loadData(list: List<Post>)
        fun progress(show: Boolean)
        fun onError(message: String)

        fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun responsePost()
        fun responseLoadDataAll()
    }
}