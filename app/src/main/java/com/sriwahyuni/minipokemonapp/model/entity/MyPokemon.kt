package com.sriwahyuni.minipokemonapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class MyPokemon(
    @ColumnInfo(name = "nickName") val nickName: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "url") val url: String?,
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
)
