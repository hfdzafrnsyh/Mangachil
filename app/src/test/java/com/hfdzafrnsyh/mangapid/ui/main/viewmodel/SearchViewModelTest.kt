package com.hfdzafrnsyh.mangapid.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import com.hfdzafrnsyh.mangapid.ui.home.HomeViewModel
import com.hfdzafrnsyh.mangapid.ui.home.search.SearchViewModel
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
class SearchViewModelTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var comicAppRepository: ComicAppRepository
    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp(){
        searchViewModel = SearchViewModel(comicAppRepository)
    }



    @Test
    fun `when search comic should Not Error and Return Success`() = runTest {

        val title = "title"
        val dummySearchComic = DataDummy.generateDummySearchComic()

        val expectedSearchComic = MutableLiveData<Wrapper<List<Data>>>()
        val data = dummySearchComic.data
        expectedSearchComic.value = Wrapper.Success(data)

        Mockito.`when`(comicAppRepository.searchComic(title)).thenReturn(expectedSearchComic)

        val searchViewModel = SearchViewModel(comicAppRepository)
        val actualSearchComic = searchViewModel.searchComic(title).getOrAwaitValue()
        Mockito.verify(comicAppRepository).searchComic(title)

        Assert.assertNotNull(actualSearchComic)
        Assert.assertTrue(actualSearchComic is Wrapper.Success)
        Assert.assertEquals(dummySearchComic.data, (actualSearchComic as Wrapper.Success).data)

    }


    @Test
    fun `get comic should Return Not Null`() = runTest {
        val dummyComic = DataDummy.generateDummyComic()

        val expectedComic = MutableLiveData<List<Data>>()
        expectedComic.value = dummyComic

        Mockito.`when`(comicAppRepository.getComic()).thenReturn(expectedComic)

        val searchViewModel = SearchViewModel(comicAppRepository)
        val actualComic = searchViewModel.getComic().getOrAwaitValue()
        Mockito.verify(comicAppRepository).getComic()

        Assert.assertNotNull(actualComic)
        Assert.assertEquals(dummyComic, actualComic)

    }


}