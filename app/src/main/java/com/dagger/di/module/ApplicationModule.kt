package com.dagger.di.module

import android.app.Application
import com.dagger.App
import com.dagger.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return app
    }
}