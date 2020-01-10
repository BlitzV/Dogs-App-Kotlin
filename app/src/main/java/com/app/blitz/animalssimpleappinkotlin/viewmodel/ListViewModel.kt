package com.app.blitz.animalssimpleappinkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData
import com.app.blitz.animalssimpleappinkotlin.model.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val dogService = DogsApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreedModelData>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(dogService.getDogs()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<DogBreedModelData>>(){
                override fun onSuccess(doglist: List<DogBreedModelData>) {
                    dogs.value = doglist
                    dogLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    dogLoadError.value = true
                    loading.value = false
                    e.printStackTrace()
                }

            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}