package com.android.hikadashi.ui.api

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hikadashi.R
import com.android.hikadashi.databinding.ViewApiBinding
import com.android.hikadashi.dto.Data
import inflate
import loadUrl

class ApiAdapter (
    var animeList : List<Data>,
    val listener: (Data) -> Unit
    ): RecyclerView.Adapter<ApiAdapter.ViewHolder>(){

        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val binding = ViewApiBinding.bind(view)
            fun bind(anime: Data){
                binding.animeTitle.text = anime.title
                anime.images?.jpg?.let { binding.animeImage.loadUrl(it.imageUrl) }
                binding.animeScore.text = anime.score.toString()
                binding.animeDesc.text = anime.synopsis
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = parent.inflate(R.layout.view_api, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(animeList[position])
            holder.itemView.setOnClickListener{
                listener(animeList[position])
            }
        }

        override fun getItemCount(): Int = animeList.size
}