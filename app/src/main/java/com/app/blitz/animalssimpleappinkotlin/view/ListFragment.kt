package com.app.blitz.animalssimpleappinkotlin.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.blitz.animalssimpleappinkotlin.R
import com.app.blitz.animalssimpleappinkotlin.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogsListAdapater = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapater
        }

        observeViewModel()

    }

    fun observeViewModel(){
        viewModel.dogs.observe(viewLifecycleOwner, Observer {dogs ->
            dogs?.let {
                recyclerView.visibility = View.VISIBLE
                dogsListAdapater.updateDogList(dogs)
            }
        })

        viewModel.dogLoadError.observe(viewLifecycleOwner, Observer {isError ->
            isError?.let {
                list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    list_error.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }
}
