package com.authbridge.daggerdemo.ui.newsDesc

import android.content.Context
import android.os.Bundle
import com.authbridge.daggerdemo.BR
import com.authbridge.daggerdemo.R
import com.authbridge.daggerdemo.common.BaseActivity
import com.authbridge.daggerdemo.databinding.ActivityNewsDescriptionBinding
import com.bumptech.glide.Glide


import javax.inject.Inject


class NewsDescription : BaseActivity<ActivityNewsDescriptionBinding, NewsDescriptionActivityViewModel>(){

    lateinit var context: Context
    lateinit var mBinding: ActivityNewsDescriptionBinding

    @Inject
    lateinit var mViewModel: NewsDescriptionActivityViewModel



    val TAG: String = "ActivityNewsDescription"
    /*var url :String = ""
    var title :String = ""
    var content :String = ""*/


    override fun getViewModel(): NewsDescriptionActivityViewModel {
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR._all
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_news_description
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = baseContext
        mBinding = getViewDataBinding()!!
        mBinding.viewModel = mViewModel
        init()
    }

    private fun init(){
        mBinding.title.text = intent.getStringExtra("title")!!
        mBinding.content.text = intent.getStringExtra("content")!!
        Glide
            .with(context)
            .load(intent.getStringExtra("url")!!)
            .into(mBinding.imageDesc);
    }

}
