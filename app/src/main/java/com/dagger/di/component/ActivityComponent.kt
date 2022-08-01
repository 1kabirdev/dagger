package com.dagger.di.component

import com.dagger.di.module.ActivityModule
import com.dagger.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}