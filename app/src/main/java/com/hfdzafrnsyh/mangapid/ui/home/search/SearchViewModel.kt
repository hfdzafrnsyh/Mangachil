package com.hfdzafrnsyh.mangapid.ui.home.search


import androidx.lifecycle.*
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import kotlinx.coroutines.launch


class SearchViewModel(private var comicAppRepository: ComicAppRepository) : ViewModel() {

    fun searchComic(title : String) : LiveData<Wrapper<List<Data>>> = comicAppRepository.searchComic(title)


    fun setComic(data : List<Data>){
        viewModelScope.launch {
            comicAppRepository.setComic(data)
        }
    }

    fun getComic() = comicAppRepository.getComic()

    fun deleteComic() {
        viewModelScope.launch {
            comicAppRepository.deleteComic()
        }
    }




}