package com.android.hikadashi.ui.api

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
    private val adapter = Adapter(emptyList()) { anime -> viewModel.navigateTo(anime) }

    private val viewModel: ApiViewModel by viewModels { ApiViewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view).apply {
            recyclerApi.adapter = adapter
            buttonAiring.setTextColor(Color.parseColor("#7fcae6"))

            viewModel.state.observe(viewLifecycleOwner) { state ->
                progress.visibility = if(state.loading) View.VISIBLE else View.GONE
                adapter.animeList = state.animes

                adapter.notifyDataSetChanged()

                buttonAiring.setOnClickListener{
                    buttonFY.setTextColor(Color.parseColor("#000000"))
                    buttonAiring.setTextColor(Color.parseColor("#7fcae6"))
                    buttonUpcoming.setTextColor(Color.parseColor("#000000"))
                    buttonMostP.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("airing")
                }

                buttonUpcoming.setOnClickListener{
                    buttonAiring.setTextColor(Color.parseColor("#000000"))
                    buttonUpcoming.setTextColor(Color.parseColor("#7fcae6"))
                    buttonMostP.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("upcoming")
                }

                buttonMostP.setOnClickListener{
                    buttonAiring.setTextColor(Color.parseColor("#000000"))
                    buttonUpcoming.setTextColor(Color.parseColor("#000000"))
                    buttonMostP.setTextColor(Color.parseColor("#7fcae6"))
                    viewModel.changeList("mostpopular")
                }

                searchAnime.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(p0: String): Boolean {
                        buttonAiring.setTextColor(Color.parseColor("#000000"))
                        buttonUpcoming.setTextColor(Color.parseColor("#000000"))
                        buttonMostP.setTextColor(Color.parseColor("#000000"))
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