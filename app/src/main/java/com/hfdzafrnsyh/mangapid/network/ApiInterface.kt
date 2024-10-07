package com.hfdzafrnsyh.mangapid.network


import com.hfdzafrnsyh.mangapid.source.model.remote.info.ChapterReadResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.info.InfoResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.comic.ComicResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.popular.PopularResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface ApiInterface {

    @GET("comic/popular/page/1")
    suspend fun comicPopular() : PopularResponse

    @GET("comic/search/{title}")
    suspend fun searchComic(
        @Path("title")
        title : String
    ) : SearchResponse

    @GET
    suspend fun infoComic(
        @Url
        endpoint : String
    ) : InfoResponse


    @GET
    suspend fun detailChapter(
        @Url
        endpoint : String
    ) : ChapterReadResponse


    @GET("comic/list?filter=manhwa")
    suspend fun getComic() : ComicResponse
}