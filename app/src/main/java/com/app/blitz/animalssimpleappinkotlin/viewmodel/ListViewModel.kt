package com.app.blitz.animalssimpleappinkotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData
import com.app.blitz.animalssimpleappinkotlin.model.DogsApiService
import com.app.blitz.animalssimpleappinkotlin.model.DogsDatabase
import com.app.blitz.animalssimpleappinkotlin.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): BaseViewModel(application) {

    private var prefsHelper = SharedPreferencesHelper(getApplication())

    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L //5 min in nano seconds

    private val dogService = DogsApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreedModelData>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val updateTime = prefsHelper.getUpdateTime()

        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            fetchFromDatabase()
        } else {
            fetchFromRemote()
        }
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun fetchFromDatabase(){
        loading.value = true
        launch {
            val dogs = DogsDatabase(getApplication()).dogDao().getAllDogs()
            dogsRetrive(dogs)
            Toast.makeText(getApplication(), "Dogs retrived from database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(dogService.getDogs()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<DogBreedModelData>>(){
                override fun onSuccess(doglist: List<DogBreedModelData>) {
                    //dogsRetrive(doglist)
                    storeDogsLocally(doglist)
                    Toast.makeText(getApplication(), "Dogs retrived from endpoint", Toast.LENGTH_SHORT).show()

                }

                override fun onError(e: Throwable) {
                    dogLoadError.value = true
                    loading.value = false
                    e.printStackTrace()
                }

            }))
    }

    private fun dogsRetrive(dogList: List<DogBreedModelData>) {
        dogs.value = dogList
        dogLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<DogBreedModelData>) {
        launch {
            val dao = DogsDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i< list.size) {
                list[i].uuid = result[i].toInt()
                i++
            }
            dogsRetrive(list)
        }

        prefsHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}