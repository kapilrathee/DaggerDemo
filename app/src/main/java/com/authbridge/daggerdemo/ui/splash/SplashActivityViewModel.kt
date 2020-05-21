package com.authbridge.daggerdemo.ui.splash

import androidx.lifecycle.ViewModel
import com.authbridge.daggerdemo.repo.IDataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SplashActivityViewModel(private val iDataManager: IDataManager) : ViewModel() {

}