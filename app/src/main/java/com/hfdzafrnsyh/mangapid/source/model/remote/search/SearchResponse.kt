package com.hfdzafrnsyh.mangapid.source.model.remote.search

import com.google.gson.annotations.SerializedName
import com.hfdzafrnsyh.mangapid.source.model.local.Data

data class SearchResponse(
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("data")
    var data : List<Data>,
    @SerializedName("message")
    var message : String,
    @SerializedName("code")
    var code : Int,

    )