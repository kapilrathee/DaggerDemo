package com.authbridge.daggerdemo.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.authbridge.daggerdemo.R
import com.authbridge.daggerdemo.databinding.ActivitySplashBinding
import com.authbridge.daggerdemo.ui.newsList.NewsList
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModel: SplashActivityViewModel

    lateinit var context: Context

    lateinit var mBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Timer("SettingUp", false).schedule(2000) {
            sendIntentMainActivity()
        }
    }

    fun sendIntentMainActivity(){
        val intent = Intent(this, NewsList::class.java)
        startActivity(intent)
        finish()
    }
}
