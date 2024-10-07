package com.hfdzafrnsyh.mangapid.ui.adapter

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import java.util.ArrayList


class PopularComicAdapter(private val listComic: List<Data>, private val itemAdapterClickCallback: ItemAdapterClickCallback) : RecyclerView.Adapter<PopularComicAdapter.PopularViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_popular_home , parent , false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(popularViewHolder: PopularViewHolder, position: Int) {
        popularViewHolder.bind(listComic[position] , itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = listComic.size

    inner class PopularViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(comic : Data , itemAdapterClickCallback: ItemAdapterClickCallback){

            val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)

            itemView.apply {
                val imageData = comic.image
                Glide.with(itemView.context)
                    .load(imageData)
                    .placeholder(R.drawable.ic_placeholder)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
                    .into(ivThumbnail)

                val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
                val tvType = itemView.findViewById<TextView>(R.id.tvType)

                tvTitle.text = comic.title
                tvType.text = comic.type

                itemView.setOnClickListener { itemAdapterClickCallback.clickComic(comic) }
            }

        }


    }


    interface ItemAdapterClickCallback{
        fun clickComic(comic : Data)
    }



}