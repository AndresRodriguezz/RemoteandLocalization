package com.example.pruebaapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.pruebaapplication.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}