package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.databinding.ChampionListItemBinding

class ChampionsAdapter(private val onChampionClick: (Int) -> Unit)
    : ListAdapter<Champion, ChampionsAdapter.ChampionItemViewHolder>(ChampionDiffCallback) {

    class ChampionItemViewHolder(private val binding: ChampionListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(champion: Champion, onChampionClick: (Int) -> Unit) {
            binding.championName.text = champion.name
            binding.championTitle.text = champion.title
            binding.championImage.load(champion.imageUrl)

            // Manejo del clic en el item
            binding.root.setOnClickListener {
                onChampionClick(champion.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionItemViewHolder {
        val binding = ChampionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionItemViewHolder, position: Int) {
        holder.bind(getItem(position), onChampionClick)
    }

    // Aquí definimos el ChampionDiffCallback
    companion object ChampionDiffCallback : DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            // Comparamos los IDs para saber si son el mismo ítem
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            // Comparamos el contenido para ver si ha cambiado
            return oldItem == newItem
        }
    }
}
