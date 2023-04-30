package com.sriwahyuni.minipokemonapp.network

import com.sriwahyuni.minipokemonapp.model.response.PokemonDetail.PokemonDetail
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.Pokemon
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("pokemon")
    fun list(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Observable<Pokemon>

    @GET("pokemon/{name}")
    fun detail(@Path("name") name: String): Observable<PokemonDetail>
}