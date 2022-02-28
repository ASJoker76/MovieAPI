package com.movie.db.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.db.R
import com.movie.db.adapter.GenreHorizontalAdapter
import com.movie.db.adapter.MovieAdapter
import com.movie.db.adapter.MovieHorizontalAdapter
import com.movie.db.databinding.FragmentDashboardBinding
import com.movie.db.model.*
import com.movie.db.utils.GridSpacingItemDecoration
import com.movie.db.viewmodel.DashboardViewModel
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter


class DashboardFragment : Fragment(){

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var genreHorizontalAdapter: GenreHorizontalAdapter
    private lateinit var movieHorizontalAdapter: MovieHorizontalAdapter
    private lateinit var binding: FragmentDashboardBinding
    private var viewModel: DashboardViewModel? = null

    private val genreArrayList: ArrayList<Genre> = java.util.ArrayList<Genre>()
    private val movieArrayList: ArrayList<ResultX> = java.util.ArrayList<ResultX>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        loadtable()

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel?.getRecyclerListDataObserver()?.observe(viewLifecycleOwner, Observer<ListMovie>{
            if(it != null) {
                //update the adapter

                movieHorizontalAdapter?.setDataList(it.results as ArrayList<Result>)

            } else {

            }
        })
        viewModel?.getRecyclerGenreDataObserver()?.observe(viewLifecycleOwner, Observer<ListGenre>{
            if(it != null) {
                //update the adapter
                genreHorizontalAdapter?.setDataList(it.genres as ArrayList<Genre>)
                genreArrayList.addAll(it.genres)
            } else {

            }
        })
        viewModel?.getRecyclerlistbygenreDataObserver()?.observe(viewLifecycleOwner, Observer<ListMovieByGendre>{
            if(it != null) {
                //update the adapter
                movieAdapter?.setDataList(it.results as ArrayList<ResultX>)
                movieArrayList.addAll(it.results)
                movieArrayList.clear()
            } else {

            }
        })


        viewModel?.panggilapilist()
        viewModel?.panggilapigenre()

        return binding.root
    }

    private fun loadtable() {
        movieHorizontalAdapter = MovieHorizontalAdapter{
                position ->  onListItemClick(position)
        }
        binding.rvNowPlaying.setAdapter(AlphaInAnimationAdapter(movieHorizontalAdapter!!))
        binding.rvNowPlaying.setHasFixedSize(true)
        binding.rvNowPlaying.setLayoutManager(CardSliderLayoutManager(requireActivity()))
        CardSnapHelper().attachToRecyclerView(binding.rvNowPlaying)
        binding.rvNowPlaying.addItemDecoration(GridSpacingItemDecoration(2, 2, true, 2))
        val alphaInAnimationAdapter = AlphaInAnimationAdapter(movieHorizontalAdapter!!)
        alphaInAnimationAdapter.setDuration(1000)
        alphaInAnimationAdapter.setInterpolator(OvershootInterpolator())
        alphaInAnimationAdapter.setFirstOnly(false)

        genreHorizontalAdapter = GenreHorizontalAdapter{
                position ->  onListItemClick2(position)
        }
        binding.rvGendre.setAdapter(AlphaInAnimationAdapter(genreHorizontalAdapter!!))
        binding.rvGendre.setHasFixedSize(true)
        binding.rvGendre.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL ,false)
        val alphaInAnimationAdapter2 = AlphaInAnimationAdapter(genreHorizontalAdapter!!)
        alphaInAnimationAdapter2.setDuration(1000)
        alphaInAnimationAdapter2.setInterpolator(OvershootInterpolator())
        alphaInAnimationAdapter2.setFirstOnly(false)

        movieAdapter = MovieAdapter{
                position ->  onListItemClick3(position)
        }
        binding.rvFilm.setAdapter(AlphaInAnimationAdapter(movieAdapter!!))
        binding.rvFilm.setHasFixedSize(true)
        binding.rvFilm.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL ,false)
        val alphaInAnimationAdapter3 = AlphaInAnimationAdapter(movieAdapter!!)
        alphaInAnimationAdapter3.setDuration(1000)
        alphaInAnimationAdapter3.setInterpolator(OvershootInterpolator())
        alphaInAnimationAdapter3.setFirstOnly(false)
    }

    private fun onListItemClick3(position: Int) {

    }

    private fun onListItemClick2(position: Int) {
        binding.tvRekomendasi.setText(genreArrayList[position].name + genreArrayList[position].id.toString())
        viewModel?.panggilapilistbygenre(genreArrayList[position].id)
    }

    private fun onListItemClick(position: Int) {
        //Toast.makeText(this, mRepos[position].name, Toast.LENGTH_SHORT).show()
    }
}