package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.NetworkResponseModel
import com.ananda.movieapidb.models.entity.Tv

data class DiscoverTvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel