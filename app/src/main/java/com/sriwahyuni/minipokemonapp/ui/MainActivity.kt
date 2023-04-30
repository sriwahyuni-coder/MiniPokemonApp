package com.sriwahyuni.minipokemonapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sriwahyuni.minipokemonapp.R
import com.sriwahyuni.minipokemonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.mainNavHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_my_pokemons,
            )
        )
        val navView: BottomNavigationView = binding.navView
        NavigationUI.setupWithNavController(navView, navController)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun toolbarList() {
        binding.navView.visibility = View.VISIBLE
        binding.toolbar.apply {
            visibility = View.VISIBLE
            title = "Pokemon"
            navigationIcon = null
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    fun toolbarMyPokemons() {
        binding.navView.visibility = View.VISIBLE
        binding.toolbar.apply {
            visibility = View.VISIBLE
            title = "My Pokemons"
            navigationIcon = null
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun toolbarDetail() {
        binding.toolbar.apply {
            visibility = View.VISIBLE
            title = "Detail"
            navigationIcon = resources.getDrawable(
                R.drawable.ic_back,
                null
            )
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
        binding.navView.visibility = View.GONE
    }

}