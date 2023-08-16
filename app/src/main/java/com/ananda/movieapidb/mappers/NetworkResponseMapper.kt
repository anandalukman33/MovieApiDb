package com.ananda.movieapidb.mappers

import com.ananda.movieapidb.models.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
    fun onLastPage(response: FROM): Boolean
}