package com.sriwahyuni.minipokemonapp.ui.list

import com.sriwahyuni.minipokemonapp.base.BasePresenter
import com.sriwahyuni.minipokemonapp.base.BaseView
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.PokemonItem

interface ListContract {
    interface View : BaseView {
        fun onListSuccess(list: List<PokemonItem>)
        fun onListFailed(message: String)
    }

    interface Presenter : ListContract, BasePresenter {
        fun getList()
    }
}