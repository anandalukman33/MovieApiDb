package com.ananda.movieapidb.view.ui.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ananda.movieapidb.models.Keyword
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.Review
import com.ananda.movieapidb.models.Video
import com.ananda.movieapidb.repository.TvRepository
import com.ananda.movieapidb.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class TvDetailViewModel @Inject constructor(
    private val repository: TvRepository
) : ViewModel() {

    private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val keywordListLiveData: LiveData<Resource<List<Keyword>>>
    val videoListLiveData: LiveData<Resource<List<Video>>>
    val reviewListLiveData: LiveData<Resource<List<Review>>>

    init {
        Timber.d("Injection TvDetailViewModel")

        this.keywordListLiveData = tvIdLiveData.switchMap {
            tvIdLiveData.value?.let {
                repository.loadKeywordList(it)
            } ?: AbsentLiveData.create()
        }

        this.videoListLiveData = tvIdLiveData.switchMap {
            tvIdLiveData.value?.let {
                repository.loadVideoList(it)
            } ?: AbsentLiveData.create()
        }

        this.reviewListLiveData = tvIdLiveData.switchMap {
            tvIdLiveData.value?.let {
                repository.loadReviewsList(it)
            } ?: AbsentLiveData.create()
        }
    }

    fun postTvId(id: Int) = tvIdLiveData.postValue(id)
}