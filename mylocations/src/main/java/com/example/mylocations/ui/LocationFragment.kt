package com.example.mylocations.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.core.BaseFragment
import com.example.mylocations.locationservice.LocationService
import com.example.mylocations.databinding.FragmentLocationBinding
import com.example.mylocations.util.Constants.ACTION_START
import com.example.mylocations.util.Constants.ACTION_STOP

class LocationFragment : BaseFragment<FragmentLocationBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentLocationBinding
        get() = FragmentLocationBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.start.setOnClickListener {
            Intent(requireActivity().applicationContext, LocationService::class.java).apply {
                action = ACTION_START
                requireActivity().startService(this)
            }
        }

        binding.stop.setOnClickListener {
            Intent(requireActivity().applicationContext, LocationService::class.java).apply {
                action = ACTION_STOP
                requireActivity().startService(this)
            }
        }
    }
}