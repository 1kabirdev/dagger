package com.dagger.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dagger.R
import com.dagger.databinding.ActivityMainBinding
import com.dagger.di.component.DaggerActivityComponent
import com.dagger.di.module.ActivityModule
import com.dagger.models.Post
import com.dagger.ui.main.presenterImpl.MainContract
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    init {
        injectDependency()
        presenter.attachView(this)
        presenter.subscribe()
        presenter.responsePost()
    }

    private fun injectDependency() {
        val mainComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule())
            .build()
        mainComponent.inject(this)
    }

    override fun loadData(list: List<Post>) {
        for (i in list) {
            Log.d("LIST", i.title)
        }
    }

    override fun progress(show: Boolean) {

    }

    override fun onError(message: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}