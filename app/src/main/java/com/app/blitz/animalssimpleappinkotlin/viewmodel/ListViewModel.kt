package com.app.blitz.animalssimpleappinkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogBreedModelData>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreedModelData("1", "Corgi", "15 years", "breedGroup", "breedFor", "Bravo", "image")
        val dog2 = DogBreedModelData("1", "pitbull", "12 years", "breedGroup", "breedFor", "Bravisimo", "image")
        val dog3 = DogBreedModelData("1", "salchicha", "10 years", "breedGroup", "breedFor", "Relajado", "image")

        val dogList = arrayListOf<DogBreedModelData>(dog1,dog2,dog3)

        dogs.value = dogList
        dogLoadError.value = false
        loading.value = false
    }
}