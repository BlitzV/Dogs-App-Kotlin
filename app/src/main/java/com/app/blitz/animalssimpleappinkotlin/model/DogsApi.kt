package com.app.blitz.animalssimpleappinkotlin.model

import io.reactivex.Single
import retrofit2.http.GET

interface DogsApi {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<DogBreedModelData>>
}