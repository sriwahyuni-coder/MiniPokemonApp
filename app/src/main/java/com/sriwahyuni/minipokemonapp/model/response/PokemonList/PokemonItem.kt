package com.sriwahyuni.minipokemonapp.model.response.PokemonList

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonItem(
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("url")
    val url: String?,
) : Parcelable