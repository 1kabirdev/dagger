package com.dagger

import android.app.Application
import com.dagger.di.component.ApplicationComponent
import com.dagger.di.component.DaggerApplicationComponent
import com.dagger.di.module.ApplicationModule

class App : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}