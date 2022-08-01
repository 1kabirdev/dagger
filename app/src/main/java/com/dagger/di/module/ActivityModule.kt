package com.dagger.di.module

import com.dagger.api.ApiService
import com.dagger.ui.main.presenterImpl.MainContract
import com.dagger.ui.main.presenterImpl.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun providerApiServices(): ApiService {
        return ApiService.create()
    }
}