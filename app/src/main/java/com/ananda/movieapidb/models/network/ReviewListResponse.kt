package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.NetworkResponseModel
import com.ananda.movieapidb.models.Review

class ReviewListResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val total_pages: Int,
    val total_results: Int
) : NetworkResponseModel