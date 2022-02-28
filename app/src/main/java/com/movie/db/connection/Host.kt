package pokemon.co.id.connection

object Host {

    const val BASE_URL = "http://api.themoviedb.org/3/"
    const val APIKEY = "api_key=859e1e2595ca61e03a724fb8889e0ddb"
    const val LANGUAGE = "&language=en-US"
    const val SEARCH_MOVIE = "search/movie?"
    const val SEARCH_TV = "search/tv?"
    const val QUERY = "&query="
    const val MOVIE_PLAYNOW = "movie/now_playing?"
    const val GENRE = "genre/movie/list?"
    const val MOVIE_POPULAR = "discover/movie?"
    const val WITH_GENRE = "&with_genres="
    const val TV_POPULAR = "discover/tv?"
    const val URLIMAGE = "https://image.tmdb.org/t/p/w780/"
    const val URLFILM = "https://www.themoviedb.org/movie/"
    const val NOTIF_DATE = "&primary_release_date.gte="
    const val REALESE_DATE = "&primary_release_date.lte="
    const val MOVIE_VIDEO = "movie/{id}/videos?"
    const val TV_VIDEO = "tv/{id}/videos?"
    const val PAGE_SIZE = 20
}