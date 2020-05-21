package com.authbridge.daggerdemo.common

import androidx.lifecycle.ViewModel
import com.authbridge.daggerdemo.repo.IDataManager

abstract class BaseViewModel(iDataManager: IDataManager) : ViewModel() {
    private var mDataManager: IDataManager? = iDataManager

    open fun getDataManager(): IDataManager? {
        return mDataManager
    }
}