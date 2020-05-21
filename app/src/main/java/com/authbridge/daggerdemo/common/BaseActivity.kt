package com.authbridge.daggerdemo.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.authbridge.daggerdemo.R
import com.authbridge.daggerdemo.Utils.Constants
import com.authbridge.daggerdemo.Utils.Logger
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection

abstract class BaseActivity<out T :ViewDataBinding, out V :BaseViewModel >: AppCompatActivity() {
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    private var dialog: ProgressDialogFragment? = null
    var isInterntAvailable: Boolean = false
    var ifInternetGoneAtInstance =false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        performDataBinding()
        mViewDataBinding?.lifecycleOwner = this
        createConnectionForInternet()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun createConnectionForInternet() {
        val connectionLiveData = ConnectionLiveData(baseContext)
        connectionLiveData.observe(this, Observer { isConnected ->
            isInterntAvailable = isConnected
            if(isConnected){
                if(ifInternetGoneAtInstance){
                    ifInternetGoneAtInstance = false
                    showSuccessSnack("Internet connected")
                }
            }else{
                ifInternetGoneAtInstance = true
                showErrorSnack("No Internet connection!!")
            }
        })
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())

        if (this.mViewModel == null) {
            this.mViewModel = getViewModel()
        } else {
            this.mViewModel = mViewModel
        }
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()

    }


    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int

    abstract @LayoutRes
    fun getLayoutId(): Int


    fun getViewDataBinding(): T? {
        return mViewDataBinding
    }


    public fun isNetworkAvailable(): Boolean {
        return isInterntAvailable
    }

    fun hideProgressDialog() {
        if (dialog != null || dialog!!.isVisible) {
            dialog!!.dismiss()
        }
    }


    fun showProgressDialog() {
        if (dialog == null) {
            dialog = ProgressDialogFragment.getProgressDialog()
        }
        if (!dialog!!.isVisible) {
            dialog!!.show(supportFragmentManager, Constants.PROGRESS_DIALOG1)
        }
    }


    fun showProgressDialog(
        message: String
    ) {
        if (dialog == null) {
            dialog = ProgressDialogFragment.getProgressDialog()
        }
        if (!dialog!!.isVisible) {
            dialog!!.showDialog(
                supportFragmentManager,
                Constants.PROGRESS_DIALOG1,
                message,
                null
            )
        }
    }

    fun showProgressDialog(
        message: String,
        listener: ProgressDialogFragment.ProgressActionListener
    ) {
        if (dialog == null) {
            dialog = ProgressDialogFragment.getProgressDialog()
        }
        if (!dialog!!.isVisible) {
            dialog!!.showDialog(
                supportFragmentManager,
                Constants.PROGRESS_DIALOG1,
                message,
                listener
            )
        }
    }

    fun showErrorSnack(str: String) {
        var snack = Snackbar.make(mViewDataBinding!!.root, str, Snackbar.LENGTH_SHORT)
        snack.view.setBackgroundColor(ContextCompat.getColor(this, R.color.error_snack_color))
        snack.duration = 2000
        snack.show()
    }

    fun showErrorSnackLong(str: String) {
        var snack = Snackbar.make(mViewDataBinding!!.root, str, Snackbar.LENGTH_SHORT)
        snack.view.setBackgroundColor(ContextCompat.getColor(this, R.color.error_snack_color))
        snack.duration = 10000
        snack.show()
    }


    fun showSuccessSnack(str: String) {
        var snack = Snackbar.make(mViewDataBinding!!.root, str, Snackbar.LENGTH_SHORT)
        snack.view.setBackgroundColor(ContextCompat.getColor(this, R.color.material_deep_teal_500))
        snack.duration = 2000
        snack.show()
    }

    fun showCustomToast(message: String) {
        var toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }


    fun showErrorLogs(TAG: String, message: String) {
        Logger.e(TAG, message)
    }

    fun showInfoLogs(TAG: String, message: String) {
        Logger.i(TAG, message)
    }

}
