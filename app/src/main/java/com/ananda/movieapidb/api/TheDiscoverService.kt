package com.ananda.movieapidb.api

import androidx.lifecycle.LiveData
import com.ananda.movieapidb.models.network.DiscoverMovieResponse
import com.ananda.movieapidb.models.network.DiscoverTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDiscoverService {

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    fun fetchDiscoverMovie(@Query("page") page: Int): LiveData<ApiResponse<DiscoverMovieResponse>>

    @GET("/3/discover/tv?language=en&sort_by=popularity.desc")
    fun fetchDiscoverTv(@Query("page") page: Int): LiveData<ApiResponse<DiscoverTvResponse>>
}