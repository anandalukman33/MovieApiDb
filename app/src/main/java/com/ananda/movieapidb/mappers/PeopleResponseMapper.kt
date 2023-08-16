package com.ananda.movieapidb.mappers

import com.ananda.movieapidb.models.network.PeopleResponse
import timber.log.Timber

class PeopleResponseMapper : NetworkResponseMapper<PeopleResponse> {
    override fun onLastPage(response: PeopleResponse): Boolean {
        Timber.d("loadPage : ${response.page}/${response.total_pages}")
        return response.page > response.total_pages
    }
}
