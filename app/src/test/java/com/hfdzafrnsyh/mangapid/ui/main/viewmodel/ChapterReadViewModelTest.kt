package com.hfdzafrnsyh.mangapid.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import com.hfdzafrnsyh.mangapid.ui.detail.InfoViewModel
import com.hfdzafrnsyh.mangapid.ui.detail.chapterRead.ChapterReadViewModel
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
class ChapterReadViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var comicAppRepository: ComicAppRepository
    private lateinit var chapterReadViewModel: ChapterReadViewModel

    @Before
    fun setUp(){
        chapterReadViewModel = ChapterReadViewModel(comicAppRepository)
    }




    @Test
    fun `when chapterRead comic Return not Null`() = runTest {
        val endpoint = "/endpoint/info"
        val dummyChapterRead = DataDummy.generateDummyDetailChapterRead()

        val expectedChapterRead = MutableLiveData<Wrapper<ChapterRead>>()
        val data = dummyChapterRead.data
        expectedChapterRead.value = Wrapper.Success(data)

        Mockito.`when`(comicAppRepository.detailComic(endpoint)).thenReturn(expectedChapterRead)

        val chapterReadViewModel = ChapterReadViewModel(comicAppRepository)
        val actualChapterRead = chapterReadViewModel.chapterRead(endpoint).getOrAwaitValue()
        Mockito.verify(comicAppRepository).detailComic(endpoint)

        Assert.assertNotNull(actualChapterRead)
        Assert.assertTrue(actualChapterRead is Wrapper.Success)
        Assert.assertEquals(dummyChapterRead.data, (actualChapterRead as Wrapper.Success).data)


    }


    @Test
    fun `when ChapterNext comic Return not Null`() = runTest {
        val id = 1
        val dummyChapterRead = DataDummy.generateDummyChapterComic()

        val expectedChapterRead = MutableLiveData<ChapterComic>()
        val data = dummyChapterRead
        expectedChapterRead.value = data

        Mockito.`when`(comicAppRepository.getChapterNext(id)).thenReturn(expectedChapterRead)

        val chapterReadViewModel = ChapterReadViewModel(comicAppRepository)
        val actualChapterRead = chapterReadViewModel.getChapterNext(id).getOrAwaitValue()
        Mockito.verify(comicAppRepository).getChapterNext(id)

        Assert.assertNotNull(actualChapterRead)
        Assert.assertEquals(dummyChapterRead,actualChapterRead)


    }


    @Test
    fun `when chapter should Not Error and Return Success`() = runTest {

        val dummyChapterComic = DataDummy.generateDummyChapter()

        val expectedChapterComic = MutableLiveData<List<ChapterComic>>()
        val data = dummyChapterComic
        expectedChapterComic.value = data

        Mockito.`when`(comicAppRepository.getChapter()).thenReturn(expectedChapterComic)

        val chapterReadViewModel = ChapterReadViewModel(comicAppRepository)
        val actualChapterComic = chapterReadViewModel.getChapter().getOrAwaitValue()
        Mockito.verify(comicAppRepository).getChapter()

        Assert.assertNotNull(actualChapterComic)
        Assert.assertEquals(dummyChapterComic, actualChapterComic)

    }

}