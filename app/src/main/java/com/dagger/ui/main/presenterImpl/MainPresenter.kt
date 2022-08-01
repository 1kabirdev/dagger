package com.dagger.ui.main.presenterImpl

import com.dagger.api.ApiService
import com.dagger.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

    private lateinit var view: MainContract.View
    private var subscription = CompositeDisposable()
    private var api: ApiService = ApiService.create()


    override fun responsePost() {
        val subscribe = api.getPostList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Post>? ->
                view.progress(false)
                view.loadData(list!!.take(20))
            }, { error ->
                view.progress(false)
                view.onError(error.localizedMessage)
            })

        subscription.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        subscription.clear()
    }

    override fun attachView(view: MainContract.View) {
        this.view = view
    }
}