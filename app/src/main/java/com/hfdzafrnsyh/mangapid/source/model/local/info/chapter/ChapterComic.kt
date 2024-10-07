package com.hfdzafrnsyh.mangapid.source.model.local.info.chapter

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "chapter")
data class ChapterComic(

    @PrimaryKey
    @field:SerializedName("id")
    var id : Int ,

    @Expose
    @field:SerializedName("name")
    var name : String,
    @Expose
    @field:SerializedName("endpoint")
    var endpoint : String
): Parcelable
