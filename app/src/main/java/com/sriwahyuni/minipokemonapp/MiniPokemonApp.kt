package com.sriwahyuni.minipokemonapp

import androidx.multidex.MultiDexApplication

class MiniPokemonApp : MultiDexApplication() {

    companion object {
        private lateinit var instance: MiniPokemonApp

        fun getApp(): MiniPokemonApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}