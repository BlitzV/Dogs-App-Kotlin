package com.app.blitz.animalssimpleappinkotlin.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreedModelData::class), version = 1)
abstract class DogsDatabase: RoomDatabase() {
    abstract fun dogDao(): DogsDao

    companion object {
        @Volatile private var instance: DogsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, DogsDatabase::class.java,
            "dogdatabase"
        ).build()
    }
}