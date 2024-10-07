package com.hfdzafrnsyh.mangapid.ui.saves

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository

class BookmarkViewModel(private val comicAppRepository: ComicAppRepository) : ViewModel() {

    fun getFavorite() : LiveData<List<ComicFavorite>> = comicAppRepository.getFavorite()

}