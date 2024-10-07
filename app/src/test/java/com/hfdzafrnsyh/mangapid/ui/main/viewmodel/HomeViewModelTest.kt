package com.hfdzafrnsyh.mangapid.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import com.hfdzafrnsyh.mangapid.ui.home.HomeViewModel
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
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var comicAppRepository: ComicAppRepository
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp(){
        homeViewModel = HomeViewModel(comicAppRepository)
    }



    @Test
    fun `when comic popular should Not Error and Return Success`() = runTest {

        val dummyComicPopular = DataDummy.generateDummyComicPopular()

        val expectedComicPopular = MutableLiveData<Wrapper<List<Data>>>()
        val data = dummyComicPopular.data
        expectedComicPopular.value = Wrapper.Success(data)

        Mockito.`when`(comicAppRepository.comicPopular()).thenReturn(expectedComicPopular)

        val homeViewModel = HomeViewModel(comicAppRepository)
        val actualComicPopular = homeViewModel.getComicPopular().getOrAwaitValue()
        Mockito.verify(comicAppRepository).comicPopular()

        Assert.assertNotNull(actualComicPopular)
        Assert.assertTrue(actualComicPopular is Wrapper.Success)
        Assert.assertEquals(dummyComicPopular.data, (actualComicPopular as Wrapper.Success).data)

    }


    @Test
    fun `when manhwa should Not Error and Return Success`() = runTest {

        val dummyManhwa = DataDummy.generateDummyManhwa()

        val expectedManhwa = MutableLiveData<Wrapper<List<Data>>>()
        val data = dummyManhwa.data
        expectedManhwa.value = Wrapper.Success(data)

        Mockito.`when`(comicAppRepository.getComicList()).thenReturn(expectedManhwa)

        val homeViewModel = HomeViewModel(comicAppRepository)
        val actualManhwa = homeViewModel.getComicList().getOrAwaitValue()
        Mockito.verify(comicAppRepository).getComicList()

        Assert.assertNotNull(actualManhwa)
        Assert.assertTrue(actualManhwa is Wrapper.Success)
        Assert.assertEquals(dummyManhwa.data, (actualManhwa as Wrapper.Success).data)

    }

}