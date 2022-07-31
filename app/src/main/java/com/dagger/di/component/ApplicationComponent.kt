package com.dagger.di.component

import com.dagger.App
import com.dagger.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: App)
}