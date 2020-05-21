package com.authbridge.daggerdemo.ui.newsDesc

import androidx.lifecycle.MutableLiveData
import com.authbridge.daggerdemo.common.BaseViewModel
import com.authbridge.daggerdemo.repo.IDataManager
import com.google.gson.JsonElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject


class NewsDescriptionActivityViewModel : BaseViewModel {
    private val TAG = "NewsDescriptionViewModel"
    private var iDataManager: IDataManager

    @Inject
    constructor(iDataManager: IDataManager) : super(iDataManager) {
        this.iDataManager = iDataManager
    }

}
