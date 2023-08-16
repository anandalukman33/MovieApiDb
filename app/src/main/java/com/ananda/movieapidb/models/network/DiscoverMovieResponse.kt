package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.NetworkResponseModel
import com.ananda.movieapidb.models.entity.Movie

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel
