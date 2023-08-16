package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.NetworkResponseModel
import com.ananda.movieapidb.models.Video

data class VideoListResponse(
    val id: Int,
    val results: List<Video>
) : NetworkResponseModel