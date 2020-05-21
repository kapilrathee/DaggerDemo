package com.authbridge.daggerdemo.repo.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.authbridge.daggerdemo.Utils.Constants
import com.authbridge.daggerdemo.repo.remote.retrofit.Api
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteHelper :IRemoteHelper {
    var api: Api

    @Inject
    constructor(api: Api) {
        this.api = api
    }

    override fun getNewsList(data: MutableLiveData<JsonElement>, country: String, api_key: String) {
        api.getNewsList(country, api_key).enqueue(object : Callback<JsonElement> {
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    data.value = Constants.getError(response)
                }
            }
        })
    }
}