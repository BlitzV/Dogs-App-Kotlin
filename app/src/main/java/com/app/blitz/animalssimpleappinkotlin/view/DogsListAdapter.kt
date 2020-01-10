package com.app.blitz.animalssimpleappinkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.blitz.animalssimpleappinkotlin.R
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData
import com.app.blitz.animalssimpleappinkotlin.util.getProgressDrawable
import com.app.blitz.animalssimpleappinkotlin.util.loadImage
import kotlinx.android.synthetic.main.row_item_list.view.*

class DogsListAdapter(val doglist: ArrayList<DogBreedModelData>): RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

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
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }

        holder.view.imageView.loadImage(doglist[position].imageUrl, getProgressDrawable(holder.view.imageView.context))
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view){

    }
}
