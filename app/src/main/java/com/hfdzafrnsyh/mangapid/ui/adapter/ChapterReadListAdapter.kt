package com.hfdzafrnsyh.mangapid.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfdzafrnsyh.mangapid.R


class ChapterReadListAdapter( private var chapterList : ArrayList<String>) :RecyclerView.Adapter<ChapterReadListAdapter.ChapterReadListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterReadListAdapter.ChapterReadListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_chapter_read , parent , false)

        return ChapterReadListViewHolder(mView)
    }

    override fun onBindViewHolder(chapterReadListViewHolder: ChapterReadListAdapter.ChapterReadListViewHolder, position: Int) {
        chapterReadListViewHolder.bind(chapterList[position])
    }

    override fun getItemCount(): Int = chapterList.size

    inner class ChapterReadListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(comic: String){


            itemView.apply {

                val ivChapterRead = itemView.findViewById<ImageView>(R.id.ivChapterRead)

                Glide.with(itemView.context)
                    .load(comic)
                    .placeholder(R.drawable.ic_placeholder)
                    .apply(RequestOptions())
                    .into(ivChapterRead)


            }

        }

    }
}