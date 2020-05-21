package com.authbridge.daggerdemo.repo.local.file_storage

import androidx.lifecycle.MutableLiveData
import com.authbridge.daggerdemo.MyApplication
import com.authbridge.daggerdemo.repo.remote.IRemoteHelper
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject
import java.io.BufferedReader
import javax.inject.Inject


class StorageHelper : IStorageHelper, IRemoteHelper {
    val TAG = "StorageHelper"

    @Inject
    constructor()

    override fun getNewsList(data: MutableLiveData<JsonElement>, country: String, api_key: String) {
        val content = loadFromAssets("news_list_response.json")
        data.value = JsonParser().parse(content)
    }



    private fun loadFromAssets(xmlFileName: String): String {
        val manager = MyApplication.application.getAssets()
        var stream = manager.open(xmlFileName)
        var content: String
        val reader = BufferedReader(stream.reader())
        try {
            content = reader.readText()
        } finally {
            reader.close()
        }
        return content
    }


}