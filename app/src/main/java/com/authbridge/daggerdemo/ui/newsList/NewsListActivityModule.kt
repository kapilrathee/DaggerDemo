package com.authbridge.daggerdemo.ui.newsList

import com.authbridge.daggerdemo.repo.IDataManager
import dagger.Module
import dagger.Provides

@Module
class NewsListActivityModule {

    @Provides
    fun provideNewsListViewModel(iDataManager: IDataManager):NewsListActivityViewModel{
        return NewsListActivityViewModel(iDataManager)
    }

}