package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.NetworkResponseModel
import com.ananda.movieapidb.models.entity.Genre

data class GenreResponse(
	val genres: List<Genre>
) : NetworkResponseModel

