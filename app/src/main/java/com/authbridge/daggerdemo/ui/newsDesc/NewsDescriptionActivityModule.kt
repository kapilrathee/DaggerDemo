package com.authbridge.daggerdemo.ui.newsDesc

import com.authbridge.daggerdemo.repo.IDataManager
import dagger.Module
import dagger.Provides

@Module
class NewsDescriptionActivityModule {

    @Provides
    fun provideNewsDescriptionViewModel(iDataManager: IDataManager):NewsDescriptionActivityViewModel{
        return NewsDescriptionActivityViewModel(iDataManager)
    }

}