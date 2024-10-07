package com.hfdzafrnsyh.mangapid.source.model.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComic(comic: List<Data>)

    @Query("SELECT * FROM comic")
    fun getComic() : LiveData<List<Data>>

    @Query("DELETE FROM comic")
    suspend fun deleteComic()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComicBookmark(favorite : ComicFavorite)


    @Query("SELECT * FROM favorite WHERE title=:title")
    fun getStatusFavoriteComic(title : String) : LiveData<ComicFavorite>

    @Query("DELETE FROM favorite WHERE id=:id")
   suspend fun deleteComicFavorite(id : String)

   @Query("SELECT * FROM favorite")
   fun getFavorite() : LiveData<List<ComicFavorite>>


   @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveChapterList(comic : ArrayList<ChapterComic>)

  @Query("SELECT * FROM chapter")
  fun getChapter() : LiveData<List<ChapterComic>>

  @Query("SELECT * FROM chapter WHERE id=:id")
  fun getChapterNext(id : Int?) : LiveData<ChapterComic>

    @Query("DELETE FROM chapter")
    suspend fun deleteChapter()


}