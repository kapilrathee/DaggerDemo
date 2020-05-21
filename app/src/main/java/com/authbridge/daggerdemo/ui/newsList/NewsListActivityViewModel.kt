package com.authbridge.daggerdemo.ui.newsList

import androidx.lifecycle.MutableLiveData
import com.authbridge.daggerdemo.common.BaseViewModel
import com.authbridge.daggerdemo.repo.IDataManager
import com.google.gson.JsonElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject


class NewsListActivityViewModel : BaseViewModel {
    private val TAG = "NewsListViewModel"
    private val viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var iDataManager: IDataManager

    var newsListResponseLiveData = MutableLiveData<JsonElement>()

    @Inject
    constructor(iDataManager: IDataManager) : super(iDataManager) {
        this.iDataManager = iDataManager
    }

    fun getNewsList(country: String, api_key: String) {
        iDataManager.getNewsList(newsListResponseLiveData, country, api_key)
    }

}
