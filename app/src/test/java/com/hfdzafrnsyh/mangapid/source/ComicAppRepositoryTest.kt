package com.hfdzafrnsyh.mangapid.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hfdzafrnsyh.mangapid.network.ApiInterface
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.room.ComicDatabase
import com.hfdzafrnsyh.mangapid.source.repository.ComicAppRepository
import com.hfdzafrnsyh.mangapid.ui.main.DataDummy
import com.hfdzafrnsyh.mangapid.ui.main.MainDispatcherRule
import com.hfdzafrnsyh.mangapid.ui.main.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ComicAppRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var comicAppRepository : ComicAppRepository

    @Mock
    lateinit var apiInterface : ApiInterface

    @Mock
    lateinit var comicDatabase : ComicDatabase



    @Before
    fun setUp(){
        apiInterface = ApiInterfaceTest()
        comicAppRepository = ComicAppRepository(apiInterface , comicDatabase)
    }

    @Test
    fun `when popular comic should Return Success`() = runTest {
        val expectComicPopular = DataDummy.generateDummyComicPopular()

        val actualPopular = comicAppRepository.comicPopular()
        actualPopular.observeForTesting {
            Assert.assertNotNull(actualPopular)
            Assert.assertEquals(
                expectComicPopular.data,
                (actualPopular.value as Wrapper.Success).data
            )
        }
    }


    @Test
    fun `when search comic should Return Success`() = runTest {
        val expectComicSearch = DataDummy.generateDummySearchComic()

        val actualSearch = comicAppRepository.searchComic("black")
        actualSearch.observeForTesting {
            Assert.assertNotNull(actualSearch)
            Assert.assertEquals(
                expectComicSearch.data,
                (actualSearch.value as Wrapper.Success).data
            )
        }
    }

    @Test
    fun `when info comic should Return Success`() = runTest {
        val expectComicInfo = DataDummy.generateDummyInfoComic()

        val actualInfo = comicAppRepository.infoComic("/endpoint/info")
        actualInfo.observeForTesting {
            Assert.assertNotNull(actualInfo)
            Assert.assertEquals(
                expectComicInfo.data,
                (actualInfo.value as Wrapper.Success).data
            )
        }
    }

    @Test
    fun `when detail chapter comic should Return Success`() = runTest {
        val expectDetail = DataDummy.generateDummyDetailChapterRead()

        val actualDetaiil = comicAppRepository.detailComic("/endpoint/info")
        actualDetaiil.observeForTesting {
            Assert.assertNotNull(actualDetaiil)
            Assert.assertEquals(
                expectDetail.data,
                (actualDetaiil.value as Wrapper.Success).data
            )
        }
    }


    @Test
    fun `when manhwa should Return Success`() = runTest {
        val expectManhwa = DataDummy.generateDummyManhwa()

        val actualManhwa = comicAppRepository.getComicList()
        actualManhwa.observeForTesting {
            Assert.assertNotNull(actualManhwa)
            Assert.assertEquals(
                expectManhwa.data,
                (actualManhwa.value as Wrapper.Success).data
            )
        }
    }



}