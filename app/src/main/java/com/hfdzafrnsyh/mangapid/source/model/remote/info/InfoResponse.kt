package com.hfdzafrnsyh.mangapid.source.model.remote.info

import com.google.gson.annotations.SerializedName
import com.hfdzafrnsyh.mangapid.source.model.local.info.InfoEntity


data class InfoResponse(
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("data")
    var data : InfoEntity,
    @SerializedName("message")
    var message : String,
    @SerializedName("code")
    var code : Int,
)
