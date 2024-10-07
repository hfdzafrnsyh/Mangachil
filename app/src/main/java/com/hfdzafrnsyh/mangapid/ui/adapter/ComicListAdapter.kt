package com.hfdzafrnsyh.mangapid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.source.model.local.Data


class ComicListAdapter(private var comicList : List<Data>, private var itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<ComicListAdapter.ComicListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListAdapter.ComicListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_list_comic , parent , false)

        return ComicListViewHolder(mView)
    }

    override fun onBindViewHolder(comicListViewHolder: ComicListAdapter.ComicListViewHolder, position: Int) {
        comicListViewHolder.bind(comicList[position],itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = comicList.size

    inner class ComicListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(comic: Data , itemAdapterClickCallback: ItemAdapterClickCallback){


            itemView.apply {

                val imageData = comic.image
                val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)

                Glide.with(itemView.context)
                    .load(imageData)
                    .placeholder(R.drawable.ic_placeholder)
                    .apply(RequestOptions())
                    .into(ivThumbnail)

                val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

                tvTitle.text = comic.title
                itemView.setOnClickListener { itemAdapterClickCallback.onClickComic(comic) }

            }

        }

    }

    interface ItemAdapterClickCallback{
        fun onClickComic(comic: Data)
    }
}