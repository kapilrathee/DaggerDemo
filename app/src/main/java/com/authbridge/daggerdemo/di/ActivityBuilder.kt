package com.authbridge.daggerdemo.di

import com.authbridge.daggerdemo.ui.newsDesc.NewsDescription
import com.authbridge.daggerdemo.ui.newsDesc.NewsDescriptionActivityModule
import com.authbridge.daggerdemo.ui.newsList.NewsList
import com.authbridge.daggerdemo.ui.newsList.NewsListActivityModule
import com.authbridge.daggerdemo.ui.splash.SplashActivity
import com.authbridge.daggerdemo.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(NewsListActivityModule::class))
    abstract fun bindNewsList(): NewsList

    @ContributesAndroidInjector(modules = arrayOf(NewsDescriptionActivityModule::class))
    abstract fun bindNewsDescription() : NewsDescription


}