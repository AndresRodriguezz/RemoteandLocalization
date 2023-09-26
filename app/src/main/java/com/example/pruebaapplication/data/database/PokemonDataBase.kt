package com.example.pruebaapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebaapplication.data.database.dao.PokemonDao
import com.example.pruebaapplication.data.database.dao.PokemonDetailDao
import com.example.pruebaapplication.data.database.entities.PokemonDetailEntity
import com.example.pruebaapplication.data.database.entities.PokemonEntity
import com.example.pruebaapplication.data.util.Constants.DATABASE_VERSION

@Database(
    entities = [
        PokemonEntity::class,
        PokemonDetailEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
}
