package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.ChampionsRepository
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionListBinding

class ChampionListFragment : Fragment() {

    private lateinit var binding: FragmentChampionListBinding
    private val repository: ChampionsRepository = InMemoryChampionsRepository.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChampionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.championRecyclerView
        val adapter = ChampionsAdapter { championId ->
            (activity as MainActivity).navigateToChampionDetail(championId)
        }

        recyclerView.adapter = adapter
        adapter.submitList(repository.getChampions())
    }
}
