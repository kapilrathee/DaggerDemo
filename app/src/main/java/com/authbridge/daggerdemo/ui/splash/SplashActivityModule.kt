package com.authbridge.daggerdemo.ui.splash

import com.authbridge.daggerdemo.repo.IDataManager
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideSplashViewModel(iDataManager: IDataManager):SplashActivityViewModel{
        return SplashActivityViewModel(iDataManager)
    }

}