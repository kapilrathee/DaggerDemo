package com.authbridge.daggerdemo

import android.app.Application
import com.authbridge.daggerdemo.di.AppComponent
import com.authbridge.daggerdemo.di.AppModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import com.authbridge.daggerdemo.di.DaggerAppComponent

class MyApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var _androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector() = _androidInjector

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var graph: AppComponent

        @JvmStatic
        lateinit var application: MyApplication

    }

    override fun onCreate() {
        super.onCreate()
        application = this
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        graph.inject(this)
    }
}