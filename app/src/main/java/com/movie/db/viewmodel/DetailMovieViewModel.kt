package com.movie.db.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movie.db.model.ListGenre
import com.movie.db.model.ListMovie
import com.movie.db.model.ListMovieByGendre
import com.movie.db.model.ResTrailer
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

class DetailMovieViewModel : ViewModel() {

    lateinit var movieLiveData: MutableLiveData<ResTrailer>



    init {
        movieLiveData = MutableLiveData<ResTrailer>()

    }

    fun getRecyclerListDataObserver(): MutableLiveData<ResTrailer>? {
        return movieLiveData
    }


    fun panggilapilist(id: Int) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getTrailer(id)
        call.enqueue(object : Callback<ResTrailer> {
            override fun onFailure(call: Call<ResTrailer>, t: Throwable) {
                movieLiveData?.postValue(null)
            }

            override fun onResponse(call: Call<ResTrailer>, response: Response<ResTrailer>) {
                if(response.isSuccessful){
                    Log.e("isi data", response.body().toString())
                    movieLiveData?.postValue(response.body())
                } else {
                    movieLiveData?.postValue(null)
                }
            }
        })
    }

}