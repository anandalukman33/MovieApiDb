package com.ananda.movieapidb.api

import androidx.lifecycle.LiveData
import com.ananda.movieapidb.models.entity.Genre
import com.ananda.movieapidb.models.network.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    @GET("/3/genre/movie/list?language=en")
    fun fetchGenreList(@Query("page") page: Int): LiveData<ApiResponse<GenreResponse>>

}