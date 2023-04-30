package com.sriwahyuni.minipokemonapp.ui.my_pokemons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sriwahyuni.minipokemonapp.databinding.ItemListBinding
import com.sriwahyuni.minipokemonapp.model.entity.MyPokemon
import com.sriwahyuni.minipokemonapp.utils.Helpers
import kotlinx.android.synthetic.main.item_list.view.*

class MyPokemonsAdapter(
    private var list: List<MyPokemon>,
) : RecyclerView.Adapter<MyPokemonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: MyPokemon) {
            itemView.apply {
                name.text = data.nickName
                Glide.with(context)
                    .load(data.url?.let { Helpers.getImageUrl(it) })
                    .into(image)
            }
        }
    }

}