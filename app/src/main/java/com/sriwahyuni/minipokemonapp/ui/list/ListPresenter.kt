package com.sriwahyuni.minipokemonapp.ui.list

import android.util.Log
import com.google.gson.Gson
import com.sriwahyuni.minipokemonapp.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListPresenter(private val view: ListContract.View) : ListContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getList() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.list()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if(!it.result?.isEmpty()!!){
                        it.result?.let { it1 -> view.onListSuccess(it1) }
                    }
                    else{
                        view.onListFailed("Tidak ada data")
                    }
                },
                {
                    view.dismissLoading()
                    view.onListFailed("Terjadi Kesalahan")
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}