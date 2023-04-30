package com.sriwahyuni.minipokemonapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sriwahyuni.minipokemonapp.databinding.ItemListBinding
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.PokemonItem
import com.sriwahyuni.minipokemonapp.utils.Helpers
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(
    private var list: List<PokemonItem>,
    private var callback: Callback
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list.get(position), callback)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: PokemonItem, callback: Callback) {
            itemView.apply {
                name.text = data.name
                Glide.with(context)
                    .load(data.url?.let { Helpers.getImageUrl(it) })
                    .into(image)

                itemView.setOnClickListener { callback.onItemClick(it, data) }
            }
        }
    }

    interface Callback {
        fun onItemClick(v: View, data: PokemonItem)
    }


}