package com.movie.db.view.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.Rating
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.movie.db.adapter.GenreHorizontalAdapter
import com.movie.db.adapter.MovieAdapter
import com.movie.db.adapter.MovieHorizontalAdapter
import com.movie.db.databinding.FragmentDashboardBinding
import com.movie.db.databinding.FragmentDetailMovieBinding
import com.movie.db.model.*
import com.movie.db.utils.GridSpacingItemDecoration
import com.movie.db.viewmodel.DashboardViewModel
import com.movie.db.viewmodel.DetailMovieViewModel
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import pokemon.co.id.connection.Host.API_YT
import pokemon.co.id.connection.Host.URLIMAGE

class DetailMovieFragment : Fragment(){

    private lateinit var key: String
    private var id_movie: Int = 0
    private lateinit var viewModel: DetailMovieViewModel
    private var posisilist: Int = 0
    private lateinit var binding: FragmentDetailMovieBinding
    private var movieArrayList: ArrayList<ResultX> = java.util.ArrayList<ResultX>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)

        loaddetail()
        viewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        viewModel?.getRecyclerListDataObserver()?.observe(viewLifecycleOwner, Observer<ResTrailer>{
            if(it != null) {
                //update the adapter
                key = it.results[0].key

            } else {

            }
        })
        viewModel.panggilapilist(id_movie)

        binding.btnTrailer.setOnClickListener {
            if(key!=null){
                openYoutubeLink(key)
            }
        }

        return binding.root
    }

    fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeID))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }

    }

    private fun loaddetail() {
        val bundle = this.arguments
        if (bundle != null) {
            movieArrayList = bundle.getParcelableArrayList<ResultX>("detailovie") as ArrayList<ResultX>
            posisilist = bundle.getInt("posisilist")

//            Log.e("posisi", posisilist.toString())
//            Log.e("isi array",movieArrayList.toString())

            id_movie = movieArrayList[posisilist].id
            binding.tvName.setText(movieArrayList[posisilist].title)
            binding.tvRating.setText(movieArrayList[posisilist].vote_average.toString() + "/10")
            binding.tvRelease.setText(movieArrayList[posisilist].release_date)
            binding.tvPopularity.setText(movieArrayList[posisilist].popularity.toString())
            binding.tvOverview.setText(movieArrayList[posisilist].overview)

            val newValue = movieArrayList[posisilist].vote_average.toFloat()
            binding.ratingBar.setNumStars(5)
            binding.ratingBar.setStepSize(0.5.toFloat())
            binding.ratingBar.setRating(newValue / 2)

            Glide.with(this)
                .load(URLIMAGE + movieArrayList[posisilist].backdrop_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgCover)

            Glide.with(this)
                .load(URLIMAGE + movieArrayList[posisilist].poster_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgPhoto)
        }
    }
}