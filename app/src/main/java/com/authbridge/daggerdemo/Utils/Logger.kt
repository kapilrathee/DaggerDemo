package com.authbridge.daggerdemo.Utils

import android.util.Log

class Logger {

    companion object {

        val logger = true
        fun e(TAG: String, message: String) {
            if (logger)
                Log.e(TAG, message)
        }

        fun i(TAG: String, message: String) {
            if (logger)
                Log.i(TAG, message)
        }

    }
}