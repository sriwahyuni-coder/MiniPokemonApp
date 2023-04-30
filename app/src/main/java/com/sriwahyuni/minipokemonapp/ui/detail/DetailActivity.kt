package com.sriwahyuni.minipokemonapp.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sriwahyuni.minipokemonapp.R
import com.sriwahyuni.minipokemonapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
    }
}