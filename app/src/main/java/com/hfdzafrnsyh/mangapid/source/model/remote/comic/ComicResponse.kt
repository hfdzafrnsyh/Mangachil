package com.hfdzafrnsyh.mangapid.source.model.remote.comic

import com.google.gson.annotations.SerializedName
import com.hfdzafrnsyh.mangapid.source.model.local.Data

data class ComicResponse(
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("data")
    var data : List<Data>,
    @SerializedName("message")
    var message : String,
    @SerializedName("code")
    var code : Int,
)