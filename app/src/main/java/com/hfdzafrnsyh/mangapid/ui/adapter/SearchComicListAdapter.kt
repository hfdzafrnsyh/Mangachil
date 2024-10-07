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


class SearchComicListAdapter(private var comicList : List<Data>, private var itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<SearchComicListAdapter.SearchComicListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchComicListAdapter.SearchComicListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_list_search_comic , parent , false)

        return SearchComicListViewHolder(mView)
    }

    override fun onBindViewHolder(searchComicListViewHolder: SearchComicListAdapter.SearchComicListViewHolder, position: Int) {
        searchComicListViewHolder.bind(comicList[position],itemAdapterClickCallback)
    }


    override fun getItemCount(): Int = comicList.size

    inner class SearchComicListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


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
                val tvType = itemView.findViewById<TextView>(R.id.tvType)
                 tvTitle.text = comic.title
                tvType.text = comic.type
                itemView.setOnClickListener { itemAdapterClickCallback.onClickComic(comic) }

            }

        }

    }

    interface ItemAdapterClickCallback{
        fun onClickComic(comic: Data)
    }
}