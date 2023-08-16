package com.ananda.movieapidb.mappers

import com.ananda.movieapidb.models.network.PersonDetail

class PersonDetailResponseMapper : NetworkResponseMapper<PersonDetail> {
    override fun onLastPage(response: PersonDetail): Boolean {
        return true
    }
}