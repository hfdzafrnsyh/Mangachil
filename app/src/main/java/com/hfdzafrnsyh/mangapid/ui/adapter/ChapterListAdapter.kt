package com.hfdzafrnsyh.mangapid.ui.adapter


import android.graphics.Paint
import android.util.Log
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfdzafrnsyh.mangapid.R


class ChapterListAdapter(private var chapterList : List<ChapterComic>, private var itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<ChapterListAdapter.ChapterListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterListAdapter.ChapterListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_list_chapter , parent , false)

        return ChapterListViewHolder(mView)
    }

    override fun onBindViewHolder(chapterListViewHolder: ChapterListAdapter.ChapterListViewHolder, position: Int) {
        chapterListViewHolder.bind(chapterList.reversed()[position],itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = chapterList.size

    inner class ChapterListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(comic: ChapterComic , itemAdapterClickCallback: ItemAdapterClickCallback){


            itemView.apply {


                val tvChapter = itemView.findViewById<TextView>(R.id.tvChapter)

                tvChapter.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                tvChapter.text = comic.name
                itemView.setOnClickListener { itemAdapterClickCallback.onClickChapter(comic) }

            }

        }

    }

    interface ItemAdapterClickCallback{
        fun onClickChapter(comic: ChapterComic)
    }
}