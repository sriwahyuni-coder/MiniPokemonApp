package com.sriwahyuni.minipokemonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sriwahyuni.minipokemonapp.model.entity.MyPokemon

@Dao
interface PokemonsDao {
    @Query("SELECT * FROM pokemon_table")
    fun getAll(): List<MyPokemon>

    @Insert
    fun insert(pokemon: MyPokemon)

}