package com.authbridge.daggerdemo.di

import com.authbridge.daggerdemo.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,AppModule::class,ActivityBuilder::class))
interface AppComponent {
    fun inject(application: MyApplication)
}