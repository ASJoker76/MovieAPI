package pokemon.co.id.connection

import com.movie.db.model.ListGenre
import com.movie.db.model.ListMovie
import com.movie.db.model.ListMovieByGendre
import pokemon.co.id.connection.Host.APIKEY
import pokemon.co.id.connection.Host.GENRE
import pokemon.co.id.connection.Host.LANGUAGE
import pokemon.co.id.connection.Host.MOVIE_PLAYNOW
import pokemon.co.id.connection.Host.MOVIE_POPULAR
import pokemon.co.id.connection.Host.WITH_GENRE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

    @GET(MOVIE_PLAYNOW + APIKEY + LANGUAGE)
    fun getListMovie(): Call<ListMovie>

    @GET(GENRE + APIKEY + LANGUAGE)
    fun getListGendre(): Call<ListGenre>

    @GET(MOVIE_POPULAR + APIKEY + LANGUAGE + WITH_GENRE + "id")
    fun getListMovieByGendre(@Query("id") id:Int): Call<ListMovieByGendre>


}