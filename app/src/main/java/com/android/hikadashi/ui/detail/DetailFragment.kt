package com.android.hikadashi.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.hikadashi.R
import com.android.hikadashi.databinding.FragmentDetailBinding
import loadUrl

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel : DetailMatchViewModel by viewModels {
        DetailMatchViewModelFactory(arguments?.getParcelable(EXTRA_ANIME)!!)
    }

    companion object{
        const val EXTRA_ANIME = "MainFragment:Data"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view).apply {

            viewModel.state.observe(viewLifecycleOwner) { state ->
                state.anime.images?.jpg?.let { detailImage.loadUrl(it.largeImageUrl) }
                detailName.text = state.anime.titleEnglish


                detailPopularity.text = state.anime.popularity.toString() + "# in Popularity"
                detailStatus.text = state.anime.status
                if(state.anime.status == "Currently Airing"){
                    detailScore.text = state.anime.score.toString()
                    detailAiringDate.text = "From " +
                            state.anime.aired?.prop?.from?.day.toString() + "/" + state.anime.aired?.prop?.from?.month.toString() + "/" + state.anime.aired?.prop?.from?.year.toString()

                }else if(state.anime.status == "Not yet aired" ){
                    detailScore.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                    detailScore.text = "Not rated yet"
                    detailAiringDate.text = state.anime.aired?.prop?.from?.day.toString() + "/" + state.anime.aired?.prop?.from?.month.toString() + "/" + state.anime.aired?.prop?.from?.year.toString()

                } else{
                    detailScore.text = state.anime.score.toString()
                    detailAiringDate.text = "From " +
                            state.anime.aired?.prop?.from?.day.toString() + "/" + state.anime.aired?.prop?.from?.month.toString() + "/" + state.anime.aired?.prop?.from?.year.toString()+
                            " to "+
                            state.anime.aired?.prop?.to?.day.toString() + "/" + state.anime.aired?.prop?.to?.month.toString() + "/" + state.anime.aired?.prop?.to?.year.toString()
                }
                detailDesc.movementMethod = ScrollingMovementMethod()
                detailDesc.text = state.anime.synopsis


                detailRating.text = state.anime.rating

            }
        }


    }
}