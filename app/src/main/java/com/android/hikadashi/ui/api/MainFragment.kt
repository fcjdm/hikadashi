package com.android.hikadashi.ui.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.hikadashi.R
import com.android.hikadashi.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val adapter = ApiAdapter(emptyList()) { anime -> viewModel.navigateTo(anime) }

    private val viewModel: ApiViewModel by viewModels { ApiViewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val binding = FragmentMainBinding.bind(view).apply {
            recyclerApi.adapter = adapter


            viewModel.state.observe(viewLifecycleOwner) { state ->
                //progress.visibility = if(state.loading) View.VISIBLE else View.GONE
                adapter.animeList = state.animes
                adapter.notifyDataSetChanged()

                /*state.navigateTo?.let {
                    findNavController().navigate(
                        R.id.action_mainFragment_to_leagueFragment2,
                        bundleOf(LeagueFragment.EXTRA_GAME to it)
                    )
                    viewModel.onNavigateDone()
                }*/
            }

        }*/
    }
}