package com.hfdzafrnsyh.mangapid.ui.main.viewmodel

import com.hfdzafrnsyh.mangapid.ui.saves.BookmarkViewModel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import com.hfdzafrnsyh.mangapid.ui.main.DataDummy
import com.hfdzafrnsyh.mangapid.ui.main.MainDispatcherRule
import com.hfdzafrnsyh.mangapid.ui.main.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var comicAppRepository: ComicAppRepository
    private lateinit var bookmarkViewModel: BookmarkViewModel

    @Before
    fun setUp(){
        bookmarkViewModel = BookmarkViewModel(comicAppRepository)
    }



    @Test
    fun `when favorite should Not Error and Return Success`() = runTest {

        val dummyFavorite = DataDummy.generateFavoriteComic()

        val expectedFavorite = MutableLiveData<List<ComicFavorite>>()
        expectedFavorite.value = dummyFavorite

        Mockito.`when`(comicAppRepository.getFavorite()).thenReturn(expectedFavorite)
        val bookmarkViewModel = BookmarkViewModel(comicAppRepository)
        val actualFavorite = bookmarkViewModel.getFavorite().getOrAwaitValue()
        Mockito.verify(comicAppRepository).getFavorite()

        Assert.assertNotNull(actualFavorite)
        Assert.assertEquals(dummyFavorite,actualFavorite)

    }



}