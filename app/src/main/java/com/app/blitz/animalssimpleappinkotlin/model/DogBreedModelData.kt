package com.app.blitz.animalssimpleappinkotlin.model

import com.google.gson.annotations.SerializedName

data class DogBreedModelData(
    @SerializedName("id")
    val breedId: String?,

    @SerializedName("name")
    val dogBreed: String?,

    @SerializedName("life_span")
    val lifeSpan: String,

    @SerializedName("breed_group")
    val breedGroup: String?,

    @SerializedName("bred_for")
    val breedFor: String?,

    @SerializedName("temperament")
    val temperament: String?,

    @SerializedName("url")
    val imageUrl: String?
)