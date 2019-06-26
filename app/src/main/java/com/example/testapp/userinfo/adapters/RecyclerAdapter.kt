package com.example.testapp.userinfo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.testapp.userinfo.R
import com.example.testapp.userinfo.objects.ResponseResult
import com.example.testapp.userinfo.objects.ResultsItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class RecyclerAdapter(val response: ResponseResult, val itemClick: (ResultsItem)->Unit)
    : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item,p0,false)
        return MyViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int = response.size

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.bindItem(response[p1])
//        p0.itemView.tag = p1
    }

    class MyViewHolder(itemView: View, val itemClick: (ResultsItem)->Unit) : RecyclerView.ViewHolder(itemView) {
        private val iconView = itemView.find<ImageView>(R.id.user_image)
        private val nameView = itemView.find<TextView>(R.id.name_id)
        private val cityView = itemView.find<TextView>(R.id.city_id)

        fun bindItem(resultsItem: ResultsItem){
            with(resultsItem){
                Picasso.with(itemView.context).load(picture?.large).into(iconView)
                val fullName =  "${name?.first} ${name?.last}"
                nameView.text = fullName
                cityView.text = "${location?.city}"
                itemView.setOnClickListener {itemClick(this)}
            }
        }

    }
}