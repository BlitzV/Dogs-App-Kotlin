package com.app.blitz.animalssimpleappinkotlin.view


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette

import com.app.blitz.animalssimpleappinkotlin.R
import com.app.blitz.animalssimpleappinkotlin.databinding.FragmentDetailBinding
import com.app.blitz.animalssimpleappinkotlin.model.DogPalette
import com.app.blitz.animalssimpleappinkotlin.util.getProgressDrawable
import com.app.blitz.animalssimpleappinkotlin.util.loadImage
import com.app.blitz.animalssimpleappinkotlin.viewmodel.detailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: detailViewModel
    private var dogUuid = 0

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProviders.of(this).get(detailViewModel::class.java)
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                dataBinding.dog = dog

                it.imageUrl?.let {
                    setupBackgroundColor(it)
                }
            }
        })
    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object: CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate {palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            val mPalette = DogPalette(intColor)
                            dataBinding.palette = mPalette
                        }
                }

            })
    }

}
