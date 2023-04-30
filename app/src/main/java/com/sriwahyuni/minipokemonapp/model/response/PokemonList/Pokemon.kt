package com.sriwahyuni.minipokemonapp.model.response.PokemonList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @Expose
    @SerializedName("results")
    val result: List<PokemonItem>? = ArrayList(),
    @Expose
    @SerializedName("count")
    val count: Int?,
    @Expose
    @SerializedName("next")
    val next: String?,
    @Expose
    @SerializedName("previous")
    val previous: String?
)
