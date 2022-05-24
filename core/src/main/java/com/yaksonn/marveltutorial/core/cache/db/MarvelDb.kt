package com.yaksonn.marveltutorial.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yaksonn.marveltutorial.core.BuildConfig
import com.yaksonn.marveltutorial.core.cache.dao.CharactersDao
import com.yaksonn.marveltutorial.core.cache.model.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class
    ],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class MarvelDb : RoomDatabase() {

    abstract val charactersDao: CharactersDao

    companion object {
        fun build(context: Context): MarvelDb = Room.databaseBuilder(
            context.applicationContext,
            MarvelDb::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration().build()
    }
}