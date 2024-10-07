package com.hfdzafrnsyh.mangapid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository

class HomeViewModel(private val comicAppRepository: ComicAppRepository) : ViewModel() {

    fun getComicPopular() : LiveData<Wrapper<List<Data>>> = comicAppRepository.comicPopular()

    fun getComicList() : LiveData<Wrapper<List<Data>>> = comicAppRepository.getComicList()
}