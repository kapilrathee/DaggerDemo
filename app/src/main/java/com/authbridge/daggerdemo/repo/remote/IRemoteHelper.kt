package com.authbridge.daggerdemo.repo.remote

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement

interface IRemoteHelper {

    fun getNewsList(data : MutableLiveData<JsonElement>, country : String, api_key : String)
}