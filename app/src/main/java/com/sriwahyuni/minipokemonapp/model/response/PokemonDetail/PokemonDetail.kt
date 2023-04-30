package com.sriwahyuni.minipokemonapp.model.response.PokemonDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class PokemonDetail(
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("height")
    val height: Int?,
    @Expose
    @SerializedName("weight")
    val weight: Int?,
    @Expose
    @SerializedName("base_experience")
    val experience: Int?,
    @Expose
    @SerializedName("types")
    val types: List<TypeResponse>?,
    ) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight!!.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height!!.toFloat() / 10)

    data class TypeResponse(
        @Expose
        @SerializedName("slot")
        var slot: Int?,
        @Expose
        @SerializedName("type")
        var types: Type?,
    )

    data class Type(
        @Expose
        @SerializedName("name")
        var name: String?,
    )
}


