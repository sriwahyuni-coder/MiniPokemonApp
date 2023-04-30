package com.sriwahyuni.minipokemonapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sriwahyuni.minipokemonapp.model.entity.MyPokemon

@Database(entities = [MyPokemon::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PokemonsDao(): PokemonsDao
}