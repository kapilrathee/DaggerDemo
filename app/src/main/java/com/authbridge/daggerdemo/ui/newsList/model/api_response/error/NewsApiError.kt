package com.authbridge.daggerdemo.ui.newsList.model.api_response.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsApiError {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}