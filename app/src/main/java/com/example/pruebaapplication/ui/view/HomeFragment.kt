package com.example.pruebaapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseFragment
import com.example.pruebaapplication.R
import com.example.pruebaapplication.databinding.FragmentHomeBinding
import com.example.pruebaapplication.domain.model.Pokemon
import com.example.pruebaapplication.ui.util.Constants.ARGUMENT_ID
import com.example.pruebaapplication.ui.util.Constants.ARGUMENT_IMAGE_URL
import com.example.pruebaapplication.ui.util.Constants.ARGUMENT_KEY
import com.example.pruebaapplication.ui.util.Constants.ARGUMENT_NAME
import com.example.pruebaapplication.ui.view.adapter.PokemonPagingAdapter
import com.example.pruebaapplication.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var adapter: PokemonPagingAdapter

    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
        initObservers()
    }

    private fun initView() {
        binding.map.setOnClickListener{
            findNavController().navigate(R.id.action_HomeFragment_to_locationFragment)
        }
    }

    private fun initAdapter() {
        adapter = PokemonPagingAdapter(PokemonPagingAdapter.COMPARATOR) { pokemon ->
           navigationToDetailFragment(pokemon)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() =
        viewModel.listPokemon.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
    }

    private fun navigationToDetailFragment(pokemon: Pokemon){
        val bundle = Bundle()
        bundle.putInt(ARGUMENT_ID, pokemon.id)
        bundle.putString(ARGUMENT_NAME, pokemon.name)
        bundle.putString(ARGUMENT_IMAGE_URL, pokemon.imageUrl)
        parentFragmentManager.setFragmentResult(ARGUMENT_KEY, bundle)
        findNavController().navigate(R.id.action_HomeFragment_to_DetailFragment, bundle)
    }
}
