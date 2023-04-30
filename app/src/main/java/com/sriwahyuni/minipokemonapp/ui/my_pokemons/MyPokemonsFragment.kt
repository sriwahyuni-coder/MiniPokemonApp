package com.sriwahyuni.minipokemonapp.ui.my_pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriwahyuni.minipokemonapp.R
import com.sriwahyuni.minipokemonapp.database.DatabaseBuilder
import com.sriwahyuni.minipokemonapp.databinding.FragmentListBinding
import com.sriwahyuni.minipokemonapp.databinding.FragmentMyPokemonsBinding
import com.sriwahyuni.minipokemonapp.model.entity.MyPokemon
import com.sriwahyuni.minipokemonapp.ui.MainActivity
import com.sriwahyuni.minipokemonapp.ui.list.ListAdapter

class MyPokemonsFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    var list: List<MyPokemon> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).toolbarMyPokemons()
        binding.swipeRefresh.isEnabled = false
        binding.emptyData.visibility = View.VISIBLE

        val db = context?.let { DatabaseBuilder.getInstance(it) }
        val dao = db!!.PokemonsDao()
        list = dao.getAll()

        if (!list.isEmpty()) {
            binding.emptyData.visibility = View.GONE
            var adapter = MyPokemonsAdapter(list)
            var layoutManager: RecyclerView.LayoutManager =
                GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = layoutManager

        }
    }
}