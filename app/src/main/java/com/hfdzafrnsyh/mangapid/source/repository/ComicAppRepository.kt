package com.hfdzafrnsyh.mangapid.source.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hfdzafrnsyh.mangapid.network.ApiInterface
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.model.local.info.InfoEntity
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.source.model.local.room.ComicDatabase



class ComicAppRepository(private val apiInterface: ApiInterface, private val comicDatabase : ComicDatabase) {

    fun comicPopular() : LiveData<Wrapper<List<Data>>> = liveData{
        emit(Wrapper.Loading)
        try {
            val response = apiInterface.comicPopular()
            val data = response.data
            emit(Wrapper.Success(data))

        }catch(e : Exception) {
            emit(Wrapper.Error("Internal Server Error"))
        }
    }


    fun searchComic(title : String) : LiveData<Wrapper<List<Data>>> = liveData {
        emit(Wrapper.Loading)
        try {
            val response = apiInterface.searchComic(title)
            val data = response.data
            emit(Wrapper.Success(data))

        }catch(e : Exception) {
            emit(Wrapper.Error("Internal Server Error"))
        }
    }

    fun infoComic(endpoint : String) : LiveData<Wrapper<InfoEntity>> = liveData {
        emit(Wrapper.Loading)
        try {
            val response = apiInterface.infoComic(endpoint)
            val data = response.data
            emit(Wrapper.Success(data))
        }catch (e : Exception){
            emit(Wrapper.Error("Internal Server Error"))
        }
    }

    fun detailComic(endpoint : String) : LiveData<Wrapper<ChapterRead>> = liveData {
        emit(Wrapper.Loading)
        try {
            val response = apiInterface.detailChapter(endpoint)
            val data = response.data
            emit(Wrapper.Success(data))
        }catch (e : Exception){
            emit(Wrapper.Error("Internal Server Error"))
        }
    }


    fun getComicList() : LiveData<Wrapper<List<Data>>> = liveData{
        emit(Wrapper.Loading)
        try {
            val response = apiInterface.getComic()
            val data = response.data
            emit(Wrapper.Success(data))
        }catch (e : Exception){
            emit(Wrapper.Error("Internal Server Error"))
        }
    }



    suspend fun setComic(comic : List<Data>){
        comicDatabase.comicDao().saveComic(comic)
    }


    fun getComic() : LiveData<List<Data>>{
        return  comicDatabase.comicDao().getComic()
    }

    suspend fun deleteComic(){
        return comicDatabase.comicDao().deleteComic()
    }

    fun getFavorite() : LiveData<List<ComicFavorite>>{
        return comicDatabase.comicDao().getFavorite()
    }

    suspend fun setFavoriteComic(favcomic : ComicFavorite){
        comicDatabase.comicDao().saveComicBookmark(favcomic)
    }

    fun getStatusFavoriteComic(title: String) : LiveData<ComicFavorite>{
        return comicDatabase.comicDao().getStatusFavoriteComic(title)
    }

    suspend fun deleteFavorite(id : String){
        return comicDatabase.comicDao().deleteComicFavorite(id)
    }

    suspend fun setChapterList(comic : ArrayList<ChapterComic>){
        return comicDatabase.comicDao().saveChapterList(comic)
    }

    fun getChapter() : LiveData<List<ChapterComic>>{
       return comicDatabase.comicDao().getChapter()
    }

    fun getChapterNext(id : Int?) : LiveData<ChapterComic>{
        return  comicDatabase.comicDao().getChapterNext(id)
    }

    suspend fun deleteChapter(){
        return comicDatabase.comicDao().deleteChapter()
    }
}