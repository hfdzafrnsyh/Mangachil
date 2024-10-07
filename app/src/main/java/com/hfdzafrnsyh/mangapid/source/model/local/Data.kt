package com.hfdzafrnsyh.mangapid.source.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "comic")
data class Data(

    @PrimaryKey
    @field:SerializedName("title")
    var title: String,


    @field:SerializedName("image")
    var image: String,



    @field:SerializedName("desc")
    var desc: String?=null,


    @field:SerializedName("type")
    var type: String?=null,



    @field:SerializedName("endpoint")
    var endpoint: String,



): Parcelable