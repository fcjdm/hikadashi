package com.android.hikadashi.ui.api

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hikadashi.R
import com.android.hikadashi.databinding.ViewApiBinding
import com.android.hikadashi.dto.anime.Anime
import inflate
import loadUrl

class ApiAdapter (
    var animeList : List<Anime>,
    val listener: (Anime) -> Unit
    ): RecyclerView.Adapter<ApiAdapter.ViewHolder>(){

        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val binding = ViewApiBinding.bind(view)
            fun bind(anime: Anime){
                binding.animeTitle.text = anime.data.title
                binding.animeImage.loadUrl(anime.data.images.jpg.imageUrl)
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