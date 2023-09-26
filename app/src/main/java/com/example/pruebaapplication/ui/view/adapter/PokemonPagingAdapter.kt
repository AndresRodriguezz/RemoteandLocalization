package com.example.pruebaapplication.ui.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.pruebaapplication.databinding.PokemonItemBinding
import com.example.pruebaapplication.domain.model.Pokemon
import com.example.pruebaapplication.ui.util.getInitials
import com.example.pruebaapplication.ui.util.toTextDrawable

class PokemonPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<Pokemon>,
    private var onSelected: (Pokemon) -> Unit) :
    PagingDataAdapter<Pokemon, PokemonPagingAdapter.PokemonViewHolder>(diffCallback) {

    class PokemonViewHolder(
        private val binding: PokemonItemBinding,
        private var onSelected: (Pokemon) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.title.text = pokemon.name
            binding.description.text = pokemon.id.toString()
            if (pokemon.name.isNotEmpty())
                binding.image.load(pokemon.imageUrl){
            } else {
                circularInitials(pokemon.name)
            }

            binding.card.setOnClickListener {
                onSelected(pokemon)
            }
        }

        private fun circularInitials(name: String) {
            val nameInitials = name.getInitials()
            val textDrawable = nameInitials.toTextDrawable(backgroundColor = Color.LTGRAY)
            binding.image.load(textDrawable) {
                scale(Scale.FILL)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onSelected
        )
    }

        object COMPARATOR : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
}
