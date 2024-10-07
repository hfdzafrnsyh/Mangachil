package com.hfdzafrnsyh.mangapid.source.model.local.info.chapter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChapterRead(
    @Expose
    @SerializedName("title")
    var title : String,
    @Expose
    @SerializedName("image")
    var image : ArrayList<String> = arrayListOf(),
)
