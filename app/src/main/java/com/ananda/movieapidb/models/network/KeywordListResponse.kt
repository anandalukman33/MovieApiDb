package com.ananda.movieapidb.models.network

import com.ananda.movieapidb.models.Keyword
import com.ananda.movieapidb.models.NetworkResponseModel

data class KeywordListResponse(
    val id: Int,
    val keywords: List<Keyword>
) : NetworkResponseModel