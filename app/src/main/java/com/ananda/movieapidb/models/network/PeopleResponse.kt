package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.NetworkResponseModel
import com.ananda.movieapidb.models.entity.Person

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel