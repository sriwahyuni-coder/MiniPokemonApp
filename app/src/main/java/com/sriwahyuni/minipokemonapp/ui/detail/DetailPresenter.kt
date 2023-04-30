package com.sriwahyuni.minipokemonapp.ui.detail

import android.content.Context
import androidx.room.Room
import com.sriwahyuni.minipokemonapp.database.AppDatabase
import com.sriwahyuni.minipokemonapp.database.DatabaseBuilder
import com.sriwahyuni.minipokemonapp.database.PokemonsDao
import com.sriwahyuni.minipokemonapp.model.entity.MyPokemon
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.PokemonItem
import com.sriwahyuni.minipokemonapp.network.HttpClient
import com.sriwahyuni.minipokemonapp.utils.Helpers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.coroutines.coroutineContext

class DetailPresenter(private val view: DetailContract.View) : DetailContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun getDetail(name: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.detail(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (!it!!.name!!.isEmpty()) {
                        it?.let { it1 -> view.onDetailSuccess(it1) }
                    } else {
                        view.onDetailFailed("Tidak ada data")
                    }
                },
                {
                    view.dismissLoading()
                    view.onDetailFailed("Terjadi Kesalahan")
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun addPokemon(context: Context, nickName: String, data: PokemonItem) {
        val database = DatabaseBuilder.getInstance(context)
        val pokemonsDao : PokemonsDao = database.PokemonsDao()
        Helpers.subscribeOnBackground {
         pokemonsDao.insert(MyPokemon(nickName, data.name, data.url))
        }
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}