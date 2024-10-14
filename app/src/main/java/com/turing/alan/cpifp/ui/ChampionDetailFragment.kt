package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionDetailBinding

class ChampionDetailFragment : Fragment() {

    private lateinit var binding: FragmentChampionDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChampionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val championId = arguments?.getInt(ARG_CHAMPION_ID)
        val champion = getChampionById(championId ?: -1)

        champion?.let {
            binding.championName.text = it.name
            binding.championTitle.text = it.title
            binding.championLore.text = it.lore
            binding.championImage.load(it.imageUrl)
        }
    }

    private fun getChampionById(championId: Int): Champion? {
        return InMemoryChampionsRepository.getInstance().getChampions().find { it.id == championId }
    }

    companion object {
        private const val ARG_CHAMPION_ID = "champion_id"

        // Método para crear una nueva instancia del fragmento con el argumento
        fun newInstance(championId: Int): ChampionDetailFragment {
            val fragment = ChampionDetailFragment()
            val args = Bundle()
            args.putInt(ARG_CHAMPION_ID, championId)
            fragment.arguments = args
            return fragment
        }
    }
}
