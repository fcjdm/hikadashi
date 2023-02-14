package com.android.hikadashi.ui.mylist

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.hikadashi.R
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.android.hikadashi.databinding.FragmentMyListBinding
import com.android.hikadashi.ui.api.Adapter
import com.android.hikadashi.ui.detail.DetailFragment


class MyListFragment : Fragment(R.layout.fragment_my_list) {


    private val adapter = Adapter(emptyList()) { anime -> viewModel.navigateTo(anime) }
    private val viewModel: MyListViewModel by viewModels { MyListViewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMyListBinding.bind(view).apply {
            recyclerMyList.adapter = adapter
            buttonWatching.setTextColor(Color.parseColor("#7fcae6"))

            viewModel.state.observe(viewLifecycleOwner) { state ->
                progress.visibility = if(state.loading) View.VISIBLE else View.GONE
                adapter.animeList = state.animes

                adapter.notifyDataSetChanged()

                buttonWatching.setOnClickListener{
                    buttonWatching.setTextColor(Color.parseColor("#7fcae6"))
                    buttonPlanToWatch.setTextColor(Color.parseColor("#000000"))
                    buttonOnHold.setTextColor(Color.parseColor("#000000"))
                    buttonDropped.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("WATCHING")

                }

                buttonPlanToWatch.setOnClickListener{
                    buttonWatching.setTextColor(Color.parseColor("#000000"))
                    buttonPlanToWatch.setTextColor(Color.parseColor("#7fcae6"))
                    buttonOnHold.setTextColor(Color.parseColor("#000000"))
                    buttonDropped.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("PLAN TO WATCH")
                }

                buttonOnHold.setOnClickListener{
                    buttonWatching.setTextColor(Color.parseColor("#000000"))
                    buttonPlanToWatch.setTextColor(Color.parseColor("#000000"))
                    buttonOnHold.setTextColor(Color.parseColor("#7fcae6"))
                    buttonDropped.setTextColor(Color.parseColor("#000000"))
                    viewModel.changeList("ON HOLD")
                }

                buttonDropped.setOnClickListener{
                    buttonWatching.setTextColor(Color.parseColor("#000000"))
                    buttonPlanToWatch.setTextColor(Color.parseColor("#000000"))
                    buttonOnHold.setTextColor(Color.parseColor("#000000"))
                    buttonDropped.setTextColor(Color.parseColor("#7fcae6"))
                    viewModel.changeList("DROPPED")
                }

                state.navigateTo?.let {
                    findNavController().navigate(
                        R.id.action_myListFragment_to_detailFragment,
                        bundleOf(DetailFragment.EXTRA_ANIME to it)
                    )
                    viewModel.onNavigateDone()
                }

            }

        }
    }
}