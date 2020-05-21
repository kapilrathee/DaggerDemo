package com.authbridge.daggerdemo.di

import android.app.Application
import android.content.Context
import com.authbridge.daggerdemo.MyApplication
import com.authbridge.daggerdemo.repo.DataManager
import com.authbridge.daggerdemo.repo.IDataManager
import com.authbridge.daggerdemo.repo.local.file_storage.IStorageHelper
import com.authbridge.daggerdemo.repo.local.file_storage.StorageHelper
import com.authbridge.daggerdemo.repo.remote.IRemoteHelper
import com.authbridge.daggerdemo.repo.remote.RemoteHelper
import com.authbridge.daggerdemo.repo.remote.retrofit.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule(val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application


    @Provides
    @Singleton
    fun provideStorageHelper(storageApiHelper: StorageHelper): IStorageHelper {
        return storageApiHelper
    }

    @Provides
    @Singleton
    fun provideRestAPIHelper(
        remoteHelper: RemoteHelper,
        storageHelper: StorageHelper
    ): IRemoteHelper {
        return remoteHelper
         //return storageHelper
    }


    @Provides
    @Singleton
    fun provideNetwork():Api{
        val retrofit: Retrofit = createNetworkClient()
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideDataManager(dataManager: DataManager): IDataManager {
        return dataManager
    }

}