package com.authbridge.daggerdemo.Utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import retrofit2.Response
import java.io.IOException

class Constants {
    companion object{
        const val PREF_NAME: String = "app_pref"
        val KEY = "383838"
        const val API_KEY = "13547c129b02404089faee489524c64d"
        const val COUNTRY = "in"

        const val PROGRESS_DIALOG1: String = "progress_dialog_type1"


        fun getError(response: Response<JsonElement>): JsonElement? {
            val parser = JsonParser()
            var error: JsonElement? = null
            try {
                error = parser.parse(response.errorBody()!!.string())
                return error
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            return error
        }

        fun getObject(json: String?, classOfT: Class<*>?): Any? {
            return Gson().fromJson(json, classOfT)
        }
    }
}