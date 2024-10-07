package com.hfdzafrnsyh.mangapid.ui.main

import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.model.local.info.InfoEntity
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.source.model.remote.info.ChapterReadResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.info.InfoResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.comic.ComicResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.popular.PopularResponse
import com.hfdzafrnsyh.mangapid.source.model.remote.search.SearchResponse

object DataDummy {

    fun generateDummyComicPopular() : PopularResponse{
        val comicList = ArrayList<Data>()
        for(i in 0..10){
            var comic = Data(
                "title",
                "http://img.thumbnail/123",
                "description",
                "Manga",
                "http://endpoint"
            )
            comicList.add(comic)
        }
        return PopularResponse(true , comicList , "Success",200)
    }


    fun generateDummySearchComic() : SearchResponse{
        val comicList = ArrayList<Data>()
        for(i in 0..10){
            var comic = Data(
                "title",
                "http://img.thumbnail/123",
                "description",
                "Manga",
                "http://endpoint"
            )
            comicList.add(comic)
        }
        return SearchResponse(true , comicList , "Success",200)
    }

    fun generateDummyInfoComic() : InfoResponse {
        val genre = arrayListOf("shounen","horor","comedy","scholl")
        val chapter : ArrayList<ChapterComic> = arrayListOf()
        var comicInfo = InfoEntity(
            "http://img.thumbnail/123",
            "title",
            "manga",
            "Yuki",
            "on-go",
            "5",
            genre,
            chapter
        )

        return InfoResponse(true , comicInfo , "Success",200)
    }

    fun generateDummyDetailChapterRead() : ChapterReadResponse {
        val imgChapter = arrayListOf<String>("http://img.thumbnail/123","http://img.thumbnail/123","http://img.thumbnail/123")
        var chapterDetail = ChapterRead(
            "title",
            imgChapter
        )

        return ChapterReadResponse(true , chapterDetail , "Success",200)
    }


    fun generateDummyManhwa() : ComicResponse {
        val comicList = ArrayList<Data>()
        for(i in 0..10){
            var comic = Data(
                "title",
                "http://img.thumbnail/123",
                "description",
                "Manga",
                "http://endpoint"
            )
            comicList.add(comic)
        }
        return ComicResponse(true , comicList , "Success",200)
    }


    fun generateDummyComic() : List<Data>{
        val comicList : MutableList<Data> = arrayListOf()
        for(i in 0..10){
            var comic = Data(
                "title",
                "http://img.thumbnail/123",
                "description",
                "Manga",
                "http://endpoint"
            )
            comicList.add(comic)
        }
        return comicList
    }


    fun generateDummyChapter() : List<ChapterComic>{
        val chapterComicList : MutableList<ChapterComic> = arrayListOf()
        for(i in 0..10){
            var chapter = ChapterComic(
                1,
                "jujutsu",
                "http://endpoint"
            )
            chapterComicList.add(chapter)
        }
        return chapterComicList
    }

    fun generateDummyChapterComic() : ChapterComic{
        val chapterComic = ChapterComic(
            1,
            "jujutsu",
            "/endpoint"
        )

        return chapterComic
    }


    fun generateFavoriteComic() : List<ComicFavorite>{
        val comicList : MutableList<ComicFavorite> = arrayListOf()
        for(i in 0..10){
            var comic = ComicFavorite(
                "id",
                "http://img.thumbnail/123",
                "title",
                "type",
                "http://endpoint",
                true
            )
            comicList.add(comic)
        }
        return comicList
    }

    fun generateStatusFavoriteComic() : ComicFavorite{


            var comic = ComicFavorite(
                "id",
                "http://img.thumbnail/123",
                "title",
                "type",
                "http://endpoint",
                true
            )


        return comic
    }


}