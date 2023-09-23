package com.example.pruebaapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater) // Inflates the view
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the reference to the View Binding instance
    }

    protected abstract val bindingInflater: (LayoutInflater) -> T
}