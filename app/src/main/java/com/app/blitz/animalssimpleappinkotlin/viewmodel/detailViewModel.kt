package com.app.blitz.animalssimpleappinkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData

class detailViewModel: ViewModel() {

    val dogLiveData = MutableLiveData<DogBreedModelData>()

    fun fetch(){
        val dog = DogBreedModelData("1", "Corgi", "15 years", "breedGroup", "breedFor", "Bravo", "image")
        dogLiveData.value = dog
    }
}