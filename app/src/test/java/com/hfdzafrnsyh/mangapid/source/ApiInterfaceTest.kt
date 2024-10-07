package com.hfdzafrnsyh.mangapid.source

import com.hfdzafrnsyh.mangapid.network.ApiInterface
import com.hfdzafrnsyh.mangapid.source.model.remote.info.ChapterReadResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.info.InfoResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.comic.ComicResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.popular.PopularResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.search.SearchResponse
import com.hfdzafrnsyh.mangapid.ui.main.DataDummy

class ApiInterfaceTest : ApiInterface {
    val dummyPopular = DataDummy.generateDummyComicPopular()
    override suspend fun comicPopular(): PopularResponse {
        return dummyPopular
    }


    val dummySearch = DataDummy.generateDummySearchComic()
    override suspend fun searchComic(title: String): SearchResponse {
       return dummySearch
    }

    val dummyInfo = DataDummy.generateDummyInfoComic()
    override suspend fun infoComic(endpoint: String): InfoResponse {
        return dummyInfo
    }

    val dummyDetailChapter = DataDummy.generateDummyDetailChapterRead()
    override suspend fun detailChapter(endpoint: String): ChapterReadResponse {
        return dummyDetailChapter
    }

    val dummyManhwa = DataDummy.generateDummyManhwa()
    override suspend fun getComic(): ComicResponse {
        return dummyManhwa
    }


}