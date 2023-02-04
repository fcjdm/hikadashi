package com.android.hikadashi.ui.api

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.hikadashi.R
import com.android.hikadashi.databinding.FragmentMainBinding
import com.android.hikadashi.ui.detail.DetailFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private val adapter = ApiAdapter(emptyList()) { anime -> viewModel.navigateTo(anime) }

    private val viewModel: ApiViewModel by viewModels { ApiViewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view).apply {
            recyclerApi.adapter = adapter
            val forYou: Button = buttonFY
            val airing: Button = buttonAiring
            val upcoming: Button = buttonUpcoming
            val mostPopular: Button = buttonMostP
            val searchView: SearchView = searchAnime
            airing.setTextColor(Color.parseColor("#7fcae6"))



            viewModel.state.observe(viewLifecycleOwner) { state ->
                progress.visibility = if(state.loading) View.VISIBLE else View.GONE
                adapter.animeList = state.animes

                adapter.notifyDataSetChanged()

                forYou.setOnClickListener{
                    forYou.setTextColor(Color.parseColor("#7fcae6"))
                    airing.setTextColor(Color.parseColor("#000000"))
                    upcoming.setTextColor(Color.parseColor("#000000"))
                    mostPopular.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("foryou")

                }

                airing.setOnClickListener{
                    forYou.setTextColor(Color.parseColor("#000000"))
                    airing.setTextColor(Color.parseColor("#7fcae6"))
                    upcoming.setTextColor(Color.parseColor("#000000"))
                    mostPopular.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("airing")
                }

                upcoming.setOnClickListener{
                    forYou.setTextColor(Color.parseColor("#000000"))
                    airing.setTextColor(Color.parseColor("#000000"))
                    upcoming.setTextColor(Color.parseColor("#7fcae6"))
                    mostPopular.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("upcoming")
                }

                mostPopular.setOnClickListener{
                    forYou.setTextColor(Color.parseColor("#000000"))
                    airing.setTextColor(Color.parseColor("#000000"))
                    upcoming.setTextColor(Color.parseColor("#000000"))
                    mostPopular.setTextColor(Color.parseColor("#7fcae6"))
                    viewModel.changeList("mostpopular")
                }

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(p0: String): Boolean {
                        forYou.setTextColor(Color.parseColor("#000000"))
                        airing.setTextColor(Color.parseColor("#000000"))
                        upcoming.setTextColor(Color.parseColor("#000000"))
                        mostPopular.setTextColor(Color.parseColor("#000000"))
                        viewModel.search(p0)
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        return false
                    }

                })

                state.navigateTo?.let {
                    findNavController().navigate(
                        R.id.action_apiFragment_to_detailFragment,
                        bundleOf(DetailFragment.EXTRA_ANIME to it)
                    )
                    viewModel.onNavigateDone()
                }
            }

        }
    }


}