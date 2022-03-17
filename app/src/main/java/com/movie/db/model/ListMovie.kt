package com.movie.db.model

data class ListMovie(
    val dates: Dates,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)