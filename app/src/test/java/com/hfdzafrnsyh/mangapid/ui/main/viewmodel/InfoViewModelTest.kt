package com.hfdzafrnsyh.mangapid.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.model.local.info.InfoEntity
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import com.hfdzafrnsyh.mangapid.ui.detail.InfoViewModel
import com.hfdzafrnsyh.mangapid.ui.home.search.SearchViewModel
import com.hfdzafrnsyh.mangapid.ui.main.DataDummy
import com.hfdzafrnsyh.mangapid.ui.main.MainDispatcherRule
import com.hfdzafrnsyh.mangapid.ui.main.getOrAwaitValue
import com.hfdzafrnsyh.mangapid.ui.saves.BookmarkViewModel
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
class InfoViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var comicAppRepository: ComicAppRepository
    private lateinit var infoViewModel: InfoViewModel

    @Before
    fun setUp(){
        infoViewModel = InfoViewModel(comicAppRepository)
    }


    @Test
    fun `when info comic Return not Null`() = runTest {
        val endpoint = "/endpoint/info"
        val dummyInfoComic = DataDummy.generateDummyInfoComic()

        val expectedInfoComic = MutableLiveData<Wrapper<InfoEntity>>()
        val data = dummyInfoComic.data
        expectedInfoComic.value = Wrapper.Success(data)

        Mockito.`when`(comicAppRepository.infoComic(endpoint)).thenReturn(expectedInfoComic)

        val infoViewModel = InfoViewModel(comicAppRepository)
        val actualInfoComic = infoViewModel.infoComic(endpoint).getOrAwaitValue()
        Mockito.verify(comicAppRepository).infoComic(endpoint)

        Assert.assertNotNull(actualInfoComic)
        Assert.assertTrue(actualInfoComic is Wrapper.Success)
        Assert.assertEquals(dummyInfoComic.data, (actualInfoComic as Wrapper.Success).data)


    }


    @Test
    fun `when get status favorite should Not Error and Return Success`() = runTest {

        val dummyFavorite = DataDummy.generateStatusFavoriteComic()
        val title = "title"

        val expectedFavorite = MutableLiveData<ComicFavorite>()
        expectedFavorite.value = dummyFavorite

        Mockito.`when`(comicAppRepository.getStatusFavoriteComic(title)).thenReturn(expectedFavorite)
        val infoViewModel = InfoViewModel(comicAppRepository)
        val actualFavorite = infoViewModel.getStatusFavoriteComic(title).getOrAwaitValue()
        Mockito.verify(comicAppRepository).getStatusFavoriteComic(title)

        Assert.assertNotNull(actualFavorite)
        Assert.assertEquals(dummyFavorite,actualFavorite)

    }

    @Test
    fun `when chapter should Not Error and Return Success`() = runTest {

        val dummyChapterComic = DataDummy.generateDummyChapter()

        val expectedChapterComic = MutableLiveData<List<ChapterComic>>()
        val data = dummyChapterComic
        expectedChapterComic.value = data

        Mockito.`when`(comicAppRepository.getChapter()).thenReturn(expectedChapterComic)

        val infoViewModel = InfoViewModel(comicAppRepository)
        val actualChapterComic = infoViewModel.getChapter().getOrAwaitValue()
        Mockito.verify(comicAppRepository).getChapter()

        Assert.assertNotNull(actualChapterComic)
        Assert.assertEquals(dummyChapterComic, actualChapterComic)

    }

}