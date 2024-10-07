package com.hfdzafrnsyh.mangapid.ui.detail.chapterRead

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import kotlinx.coroutines.launch

class ChapterReadViewModel(private val comicAppRepository: ComicAppRepository) : ViewModel() {


    fun getChapter() : LiveData<List<ChapterComic>> = comicAppRepository.getChapter()


    fun chapterRead(endpoint: String) : LiveData<Wrapper<ChapterRead>> = comicAppRepository.detailComic(endpoint)


    fun getChapterNext(id : Int?) : LiveData<ChapterComic> = comicAppRepository.getChapterNext(id)

}