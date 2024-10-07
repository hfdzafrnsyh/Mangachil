package com.hfdzafrnsyh.mangapid.ui.adapter

import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfdzafrnsyh.mangapid.R


class BookmarkListAdapter(private var comicList : List<ComicFavorite>, private var itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<BookmarkListAdapter.BookmarkListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkListAdapter.BookmarkListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_list_bookmark_favorite , parent , false)

        return BookmarkListViewHolder(mView)
    }

    override fun onBindViewHolder(bookmarkListViewHolder: BookmarkListAdapter.BookmarkListViewHolder, position: Int) {
        bookmarkListViewHolder.bind(comicList[position],itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = comicList.size

    inner class BookmarkListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(comic: ComicFavorite , itemAdapterClickCallback: ItemAdapterClickCallback){


            itemView.apply {

                val imageData = "${comic.thumbnail}"
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
        fun onClickComic(comic: ComicFavorite)
    }
}