package com.dagger.ui.main.presenterImpl

import com.dagger.api.ApiService
import com.dagger.models.Album
import com.dagger.models.DetailsViewModel
import com.dagger.models.Post
import com.dagger.models.User
import io.reactivex.Observable
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

    override fun responseLoadDataAll() {
        val subscribe = Observable.zip(api.getPostList(), api.getUserList(), api.getAlbumList(),
            object : Function3<List<Post>, List<User>, List<Album>, DetailsViewModel> {
                override fun invoke(
                    posts: List<Post>,
                    users: List<User>,
                    albums: List<Album>
                ): DetailsViewModel {
                    return createDetailsViewModel(posts, users, albums)
                }
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ model: DetailsViewModel? ->
                view.loadDataAllSuccess(model!!)
            }, { error ->
                print("ERROR: ${error.localizedMessage}")
            })

        subscription.add(subscribe)
    }


    private fun createDetailsViewModel(
        posts: List<Post>,
        users: List<User>,
        albums: List<Album>
    ): DetailsViewModel {
        val postList = posts.take(30)
        val userList = users.take(30)
        val albumList = albums.take(30)
        return DetailsViewModel(postList, userList, albumList)
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