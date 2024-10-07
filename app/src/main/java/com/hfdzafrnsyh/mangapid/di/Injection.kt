package com.hfdzafrnsyh.mangapid.di

import android.content.Context
import com.hfdzafrnsyh.mangapid.network.RetrofitClient
import com.hfdzafrnsyh.mangapid.source.model.local.room.ComicDatabase
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository

object Injection {
    fun provideRepository(context: Context) : ComicAppRepository {

        val retrofitClient = RetrofitClient.getApiService()
        val storyDatabase = ComicDatabase.getInstance(context)

        return ComicAppRepository(retrofitClient, storyDatabase)
    }
}