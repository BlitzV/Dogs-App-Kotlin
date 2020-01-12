package com.app.blitz.animalssimpleappinkotlin.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogsDao {

    @Insert
    suspend fun insertAll(vararg dogs: DogBreedModelData): List<Long>

    @Query("SELECT * FROM dogbreedmodeldata")
    suspend fun getAllDogs(): List<DogBreedModelData>

    @Query("SELECT * FROM dogbreedmodeldata WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreedModelData

    @Query("DELETE FROM dogbreedmodeldata")
    suspend fun deleteAllDogs()

}