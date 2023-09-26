package com.example.pruebaapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.viewModels
import coil.load
import com.example.pruebaapplication.R
import com.example.pruebaapplication.databinding.FragmentDetailBinding
import com.example.pruebaapplication.domain.model.PokemonDetail
import com.example.pruebaapplication.ui.view.utils.Constants.ARGUMENT_ID
import com.example.pruebaapplication.ui.view.utils.Constants.ARGUMENT_IMAGE_URL
import com.example.pruebaapplication.ui.view.utils.Constants.ARGUMENT_NAME
import com.example.pruebaapplication.ui.viewmodel.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var nameArg: String
    private lateinit var imageUrlArg: String
    private lateinit var id: String

    override val bindingInflater: (LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserves()
    }

    private fun initView() {
        id = arguments?.getInt(ARGUMENT_ID).toString()
        nameArg = arguments?.getString(ARGUMENT_NAME) ?: ""
        imageUrlArg = arguments?.getString(ARGUMENT_IMAGE_URL) ?: ""
    }

    private fun initObserves() {
        viewModel.PokemonDetail(nameArg)

        viewModel.pokemonDetail.observe(viewLifecycleOwner) { pokemonDetail ->
            setView(pokemonDetail)
        }

        viewModel.pokemonDetailFailure.observe(viewLifecycleOwner) {
            if (it) {
                viewFailure()
            }
        }
    }

    private fun viewFailure() {
        with(binding) {
            index.visibility = GONE
            name.text = getString(R.string.something_wrong_try_later)
            image.visibility = GONE
            height.visibility = GONE
            weight.visibility = GONE
            experience.visibility = GONE
            type.visibility = GONE
        }
    }

    private fun setView(pokemonDetail: PokemonDetail) {
        val finalList = pokemonDetail.types.map { it.type.name }
        with(binding) {
            index.text = getString(R.string.number_index, id)
            name.text = pokemonDetail.name
            image.load(imageUrlArg)
            height.text = getString(R.string.height, pokemonDetail.height.toString())
            weight.text = getString(R.string.weight, pokemonDetail.weight.toString())
            experience.text = getString(R.string.experience, pokemonDetail.experience.toString())
            type.text = getString(R.string.types, finalList.joinToString(", "))
        }
    }
}