package com.hfdzafrnsyh.mangapid.source

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfdzafrnsyh.mangapid.di.Injection
import com.hfdzafrnsyh.mangapid.ui.detail.InfoViewModel
import com.hfdzafrnsyh.mangapid.ui.detail.chapterRead.ChapterReadViewModel
import com.hfdzafrnsyh.mangapid.ui.home.HomeViewModel
import com.hfdzafrnsyh.mangapid.ui.home.search.SearchViewModel
import com.hfdzafrnsyh.mangapid.ui.saves.BookmarkViewModel

class ViewModelFactory(private val context : Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                return SearchViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(InfoViewModel::class.java) -> {
                return InfoViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(ChapterReadViewModel::class.java) -> {
                return ChapterReadViewModel(Injection.provideRepository(context)) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}