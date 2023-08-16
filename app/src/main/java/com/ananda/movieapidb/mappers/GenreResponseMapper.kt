package com.ananda.movieapidb.mappers

import com.ananda.movieapidb.models.network.GenreResponse

class GenreResponseMapper : NetworkResponseMapper<GenreResponse> {
    override fun onLastPage(response: GenreResponse): Boolean = true

}