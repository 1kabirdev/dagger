package com.dagger

import android.app.Application
import com.dagger.di.component.ApplicationComponent

class App:Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
    }
}