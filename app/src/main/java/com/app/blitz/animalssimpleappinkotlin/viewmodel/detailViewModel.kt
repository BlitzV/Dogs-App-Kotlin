package com.app.blitz.animalssimpleappinkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData
import com.app.blitz.animalssimpleappinkotlin.model.DogsDatabase
import kotlinx.coroutines.launch

class detailViewModel(application: Application): BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreedModelData>()

    fun fetch(uuid: Int){
        launch {
            val dog = DogsDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog
        }
    }
}