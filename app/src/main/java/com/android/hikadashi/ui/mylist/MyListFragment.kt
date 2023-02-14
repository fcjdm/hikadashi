package com.android.hikadashi.ui.mylist

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.hikadashi.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.hikadashi.databinding.FragmentMyListFragmentBinding
import com.android.hikadashi.ui.api.Adapter


class MyListFragment : Fragment(R.layout.fragment_my_list_fragment) {


    private val adapter = Adapter(emptyList()) { anime -> viewModel.navigateTo(anime) }
    private val viewModel: MyListViewModel by viewModels { MyListViewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMyListFragmentBinding.bind(view).apply {
            recyclerMyList.adapter = adapter

            viewModel.state.observe(viewLifecycleOwner) { state ->
                progress.visibility = if(state.loading) View.VISIBLE else View.GONE
                adapter.animeList = state.animes

                adapter.notifyDataSetChanged()

                }

        }
    }
}