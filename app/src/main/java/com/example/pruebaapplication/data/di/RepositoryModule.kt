package com.example.pruebaapplication.data.di

import com.example.pruebaapplication.data.repository.PokemonDetailRepository
import com.example.pruebaapplication.data.repository.PokemonDetailRepositoryImpl
import com.example.pruebaapplication.data.repository.PokemonRepository
import com.example.pruebaapplication.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {

    @Binds
     fun provideRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
     ): PokemonRepository

    @Binds
    fun provideDetailRepository(
        pokemonDetailRepositoryImpl: PokemonDetailRepositoryImpl
    ): PokemonDetailRepository
}
