package com.hfdzafrnsyh.mangapid.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.info.InfoEntity
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import kotlinx.coroutines.launch


class InfoViewModel(private val comicAppRepository: ComicAppRepository) : ViewModel() {

    fun infoComic(endpoint: String) : LiveData<Wrapper<InfoEntity>> = comicAppRepository.infoComic(endpoint)


    fun setFavoriteComic(favcomic : ComicFavorite) {
        viewModelScope.launch {
            comicAppRepository.setFavoriteComic(favcomic)
        }
    }


    fun getStatusFavoriteComic(title : String) = comicAppRepository.getStatusFavoriteComic(title)

    fun deleteFavorite(id : String) {
        viewModelScope.launch {
            comicAppRepository.deleteFavorite(id)
        }
    }

    fun setChapterComic(comic : ArrayList<ChapterComic>){
        viewModelScope.launch {
            comicAppRepository.setChapterList(comic)
        }
    }

    fun getChapter() : LiveData<List<ChapterComic>> = comicAppRepository.getChapter()

    fun deleteChapter() {
        viewModelScope.launch {
            comicAppRepository.deleteChapter()
        }
    }

}