package com.hfdzafrnsyh.mangapid.source.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "favorite")
data class ComicFavorite(

    @PrimaryKey
    @field:SerializedName("id")
    var id : String ,


    @field:SerializedName("thumbnail")
    var thumbnail : String?=null ,

    @field:SerializedName("title")
    var title : String?=null,


    @field:SerializedName("type")
    var type : String?=null,


    @field:SerializedName("endpoint")
    var endpoint : String?=null,

    @field:SerializedName("favorite")
    var favorite: Boolean?=false



) : Parcelable
