package com.movie.db.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movie.db.model.ListGenre
import com.movie.db.model.ListMovie
import com.movie.db.model.ListMovieByGendre
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import pokemon.co.id.connection.Host
import pokemon.co.id.connection.RetroInstance
import pokemon.co.id.connection.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.ArrayList

class DashboardViewModel : ViewModel() {

    lateinit var movieLiveData: MutableLiveData<ListMovie>
    lateinit var genreLiveData: MutableLiveData<ListGenre>
    lateinit var listbygenreLiveData: MutableLiveData<ListMovieByGendre>

    init {
        movieLiveData = MutableLiveData<ListMovie>()
        genreLiveData = MutableLiveData<ListGenre>()
        listbygenreLiveData = MutableLiveData<ListMovieByGendre>()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<ListMovie>? {
        return movieLiveData
    }

    fun getRecyclerGenreDataObserver(): MutableLiveData<ListGenre>? {
        return genreLiveData
    }

    fun getRecyclerlistbygenreDataObserver(): MutableLiveData<ListMovieByGendre>? {
        return listbygenreLiveData
    }


    fun panggilapilist() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getListMovie()
        call.enqueue(object : Callback<ListMovie> {
            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                movieLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    movieLiveData?.postValue(response.body())
                } else {
                    movieLiveData?.postValue(null)
                }
            }
        })
    }

    fun panggilapigenre() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getListGendre()
        call.enqueue(object : Callback<ListGenre> {
            override fun onFailure(call: Call<ListGenre>, t: Throwable) {
                genreLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<ListGenre>, response: Response<ListGenre>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    genreLiveData?.postValue(response.body())
                } else {
                    genreLiveData?.postValue(null)
                }
            }
        })
    }

    fun panggilapilistbygenre(id : Int) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getListMovieByGendre(id)
        call.enqueue(object : Callback<ListMovieByGendre> {
            override fun onFailure(call: Call<ListMovieByGendre>, t: Throwable) {
                listbygenreLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<ListMovieByGendre>, response: Response<ListMovieByGendre>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    listbygenreLiveData?.postValue(response.body())
                } else {
                    listbygenreLiveData?.postValue(null)
                }
            }
        })
    }

}