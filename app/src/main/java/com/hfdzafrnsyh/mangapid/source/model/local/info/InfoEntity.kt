package com.hfdzafrnsyh.mangapid.source.model.local.info

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import kotlinx.parcelize.Parcelize
import kotlin.collections.ArrayList

@Parcelize
data class InfoEntity(

        @Expose
        @SerializedName("thumbnail")
        var thumbnail : String ,
        @Expose
        @SerializedName("title")
        var title : String,
        @Expose
        @SerializedName("type")
        var type : String,
        @Expose
        @SerializedName("author")
        var author : String,
        @Expose
        @SerializedName("status")
         var status : String,
        @Expose
        @SerializedName("rating")
        var rating : String,
        @Expose
        @SerializedName("genre")
        var genre : ArrayList<String> = arrayListOf(),
        @Expose
        @SerializedName("chapter_list")
        var chapter_list : ArrayList<ChapterComic>


) : Parcelable
