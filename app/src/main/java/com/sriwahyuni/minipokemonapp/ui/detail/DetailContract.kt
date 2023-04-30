package com.sriwahyuni.minipokemonapp.ui.detail

import android.content.Context
import com.sriwahyuni.minipokemonapp.base.BasePresenter
import com.sriwahyuni.minipokemonapp.base.BaseView
import com.sriwahyuni.minipokemonapp.model.response.PokemonDetail.PokemonDetail
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.PokemonItem

interface DetailContract {
    interface View : BaseView {
        fun onDetailSuccess(data: PokemonDetail)
        fun onDetailFailed(message: String)
    }

    interface Presenter : DetailContract, BasePresenter {
        fun getDetail(name: String)
        fun addPokemon(conext: Context, nickName: String, data: PokemonItem)
    }
}