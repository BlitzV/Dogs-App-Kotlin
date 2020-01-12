package com.app.blitz.animalssimpleappinkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.blitz.animalssimpleappinkotlin.R
import com.app.blitz.animalssimpleappinkotlin.databinding.RowItemListBinding
import com.app.blitz.animalssimpleappinkotlin.model.DogBreedModelData
import com.app.blitz.animalssimpleappinkotlin.util.getProgressDrawable
import com.app.blitz.animalssimpleappinkotlin.util.loadImage
import kotlinx.android.synthetic.main.row_item_list.view.*

class DogsListAdapter(val doglist: ArrayList<DogBreedModelData>): RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(), DogClickLister {

    fun updateDogList(newDogsList: List<DogBreedModelData>) {
        doglist.clear()
        doglist.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RowItemListBinding>(inflater, R.layout.row_item_list, parent,false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doglist.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = doglist[position]
        holder.view.listener = this
    }

    class DogViewHolder(var view: RowItemListBinding) : RecyclerView.ViewHolder(view.root){}

    override fun onDogClicked(v: View) {
        val action = ListFragmentDirections.actionDetailFragment()
        action.dogUuid = v.dogID.text.toString().toInt()
        Navigation.findNavController(v).navigate(action)
    }
}
