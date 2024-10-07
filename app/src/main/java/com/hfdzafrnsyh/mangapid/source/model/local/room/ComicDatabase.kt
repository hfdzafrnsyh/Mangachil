package com.hfdzafrnsyh.mangapid.source.model.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic


@Database(
    entities = [Data::class,ComicFavorite::class, ChapterComic::class],
    version = 8,
    exportSchema = false
)
abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDao() : ComicDao

    companion object {
        @Volatile
        private var INSTANCE: ComicDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): ComicDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ComicDatabase::class.java, "DB-comic"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }

        }
    }

}