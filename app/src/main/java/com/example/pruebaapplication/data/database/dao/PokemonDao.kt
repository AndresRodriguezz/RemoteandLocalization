package com.example.pruebaapplication.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebaapplication.data.database.entities.PokemonDetailEntity
import com.example.pruebaapplication.data.database.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): LiveData<List<PokemonEntity>>
}

@Dao
interface PokemonDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemonDetail: PokemonDetailEntity)

    @Query("SELECT * FROM pokemon_detail WHERE name = :name")
    fun getPokemonDetailByName(name: String): LiveData<PokemonDetailEntity?>

}