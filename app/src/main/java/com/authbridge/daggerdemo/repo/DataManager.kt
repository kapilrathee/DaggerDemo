package com.authbridge.daggerdemo.repo

import androidx.lifecycle.MutableLiveData
import com.authbridge.daggerdemo.repo.remote.IRemoteHelper
import com.google.gson.JsonElement
import javax.inject.Inject

class DataManager: IDataManager {
    var remoteHelper: IRemoteHelper

    @Inject
    constructor(
        remoteHelper: IRemoteHelper
    ) {
        this.remoteHelper = remoteHelper
    }

    override fun getNewsList(data: MutableLiveData<JsonElement>, country: String, api_key: String) {
        remoteHelper.getNewsList(data,country,api_key)
    }

}