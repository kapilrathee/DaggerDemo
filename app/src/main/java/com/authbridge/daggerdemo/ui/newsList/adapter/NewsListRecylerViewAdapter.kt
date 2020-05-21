package com.authbridge.daggerdemo.ui.newsList.adapter

import android.content.Context
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.authbridge.daggerdemo.R
import com.authbridge.daggerdemo.ui.newsList.model.api_response.Article
import com.authbridge.footprintsdigital.view.addresses.model.NewsModelList
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class NewsListRecylerViewAdapter(private val context: Context,
                                 private val newsList: ArrayList<Article>,
                                 private val adapterClickListner: AdapterClickListner)
    : RecyclerView.Adapter<NewsListRecylerViewAdapter.ViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        val view = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false)
        return ViewHoler(view)
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {

        if(null!=newsList[position].title){
            holder.textViewTitle.text = newsList[position].title!!
        }

        if(null!=newsList[position].description){
            holder.textViewDescription.text = newsList[position].description!!
        }

        if(null!=newsList[position].source && null!=newsList[position].source!!.name){
            holder.source.text = newsList[position].source!!.name!! +"  "+newsList[position].author
        }

        if (null!=newsList[position].publishedAt){
            holder.date.text = newsList[position].publishedAt!!
        }


        Glide
            .with(context)
            .load(newsList[position].urlToImage)
            .into(holder.imageView);

        holder.relativeLayoutItem.setOnClickListener { view: View? ->
            if(null!=newsList[position].content){
                adapterClickListner.onItemClick(position,newsList[position].urlToImage!!,newsList[position].title!!,newsList[position].content!!)
            }else{
                var snack = view?.let { Snackbar.make(it, "No content available for this news.", Snackbar.LENGTH_SHORT) }
                if (snack != null) {
                    snack.view.setBackgroundColor(ContextCompat.getColor(context, R.color.error_snack_color))
                    snack.duration = 2000
                    snack.show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class ViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val relativeLayoutItem : RelativeLayout
        val imageView: ImageView
        val textViewDescription: TextView
        val textViewTitle : TextView
        val source : TextView
        val date : TextView

        init {
            textViewTitle = itemView.findViewById(R.id.title)
            textViewDescription = itemView.findViewById(R.id.description)
            imageView = itemView.findViewById(R.id.imageView)
            relativeLayoutItem = itemView.findViewById(R.id.relativeLayoutItem)
            source = itemView.findViewById(R.id.source)
            date = itemView.findViewById(R.id.date)
        }
    }

    interface AdapterClickListner {
        fun onItemClick(position:Int, url : String, title: String, content: String)
    }
}
