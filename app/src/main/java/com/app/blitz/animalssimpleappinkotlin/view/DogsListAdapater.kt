package com.app.blitz.animalssimpleappinkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.blitz.animalssimpleappinkotlin.R
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.row_item_list.view.*

class DogsListAdapater(val doglist: ArrayList<DogBreedModelData>): RecyclerView.Adapter<DogsListAdapater.DogViewHolder>() {

    fun updateDogList(newDogsList: List<DogBreedModelData>) {
        doglist.clear()
        doglist.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_item_list, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doglist.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = doglist[position].dogBreed
        holder.view.text.text = doglist[position].lifeSpan
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view){

    }
}
