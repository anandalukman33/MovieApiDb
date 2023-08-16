package com.ananda.movieapidb.mappers

import com.ananda.movieapidb.models.network.VideoListResponse

class VideoResponseMapper : NetworkResponseMapper<VideoListResponse> {
    override fun onLastPage(response: VideoListResponse): Boolean {
        return true
    }
}