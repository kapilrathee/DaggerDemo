package com.authbridge.daggerdemo.ui.newsList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.authbridge.daggerdemo.BR
import com.authbridge.daggerdemo.R
import com.authbridge.daggerdemo.Utils.Constants
import com.authbridge.daggerdemo.Utils.Logger
import com.authbridge.daggerdemo.common.BaseActivity
import com.authbridge.daggerdemo.databinding.ActivityNewListBinding
import com.authbridge.daggerdemo.ui.newsDesc.NewsDescription
import com.authbridge.daggerdemo.ui.newsList.adapter.NewsListRecylerViewAdapter
import com.authbridge.daggerdemo.ui.newsList.model.api_response.NewsApiResponse
import com.authbridge.daggerdemo.ui.newsList.model.api_response.error.NewsApiError
import com.authbridge.footprintsdigital.view.addresses.model.NewsModelList
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.json.JSONObject
import javax.inject.Inject




class NewsList : BaseActivity<ActivityNewListBinding, NewsListActivityViewModel>(), NewsListRecylerViewAdapter.AdapterClickListner{

    lateinit var context: Context
    lateinit var mBinding: ActivityNewListBinding
    private lateinit var adapter: NewsListRecylerViewAdapter
    lateinit var list: ArrayList<NewsModelList>

    @Inject
    lateinit var mViewModel: NewsListActivityViewModel



    val TAG: String = "ActivityNewsList"



    override fun getViewModel(): NewsListActivityViewModel {
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR._all
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_new_list
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = baseContext
        mBinding = getViewDataBinding()!!
        mBinding.viewModel = mViewModel
        observeNewsListData()
        getNewsList()
    }

    private fun getNewsList(){
        showProgressDialog()
        mViewModel.getNewsList(Constants.COUNTRY,Constants.API_KEY)
       /* if(isNetworkAvailable()){
            showProgressDialog()
            mViewModel.getNewsList(Constants.COUNTRY,Constants.API_KEY)
        }else{
            showErrorSnack("No Internet connection!")
        }*/

    }


    private fun observeNewsListData() {
        mViewModel.newsListResponseLiveData.observe(this, Observer
        {
            onNewsListReceived(it)}
        )
    }

    private fun onNewsListReceived(jsonElement: JsonElement) {
        hideProgressDialog()
        Logger.e(TAG, jsonElement.toString())
        val response = JSONObject(jsonElement.toString())
        //parse json with Gson (in success case or in error case)
        if(response.getString("status").equals("ok")){
            val newsApiObject = Constants.getObject(jsonElement.toString(),NewsApiResponse::class.java) as NewsApiResponse
            setAdapterToList(newsApiObject)
        }else{
            val newsApiErrorObject = Constants.getObject(jsonElement.toString(),NewsApiError::class.java) as NewsApiError
            showErrorSnack(newsApiErrorObject.message!!)
        }

    }



    override fun onItemClick(position: Int,url : String, title : String, content : String) {
        val intent = Intent(this, NewsDescription::class.java)
        intent.putExtra("url",url)
        intent.putExtra("title",title)
        intent.putExtra("content",content)
        startActivity(intent)
    }

    private fun setAdapterToList(newsApiObject:NewsApiResponse) {
        adapter = NewsListRecylerViewAdapter(context, newsApiObject.articles!!, this)
        mBinding.recylerViewAddressTypee.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.recylerViewAddressTypee.adapter = adapter
    }

}
