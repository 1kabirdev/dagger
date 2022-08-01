package com.dagger.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dagger.R
import com.dagger.databinding.ActivityMainBinding
import com.dagger.di.component.DaggerActivityComponent
import com.dagger.di.module.ActivityModule
import com.dagger.models.DetailsViewModel
import com.dagger.models.Post
import com.dagger.ui.main.adapter.AdapterPost
import com.dagger.ui.main.presenterImpl.MainContract
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding
    lateinit var adapterPost: AdapterPost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterPost = AdapterPost()
    }

    init {
        injectDependency()
        presenter.attachView(this)
        presenter.subscribe()
        presenter.responsePost()
        presenter.responseLoadDataAll()
    }

    private fun injectDependency() {
        val mainComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule())
            .build()
        mainComponent.inject(this)
    }

    override fun loadData(list: List<Post>) {
        adapterPost.addPost(list)
        binding.recyclerViewPost.adapter = adapterPost
    }

    override fun progress(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun onError(message: String) {

    }

    override fun loadDataAllSuccess(model: DetailsViewModel) {
        Log.d("MODEL_TO_JSON", model.toJson())
        Log.d("POSTS", model.posts.toString())
        Log.d("USERS", model.users.toString())
        Log.d("ALBUMS", model.albums.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}