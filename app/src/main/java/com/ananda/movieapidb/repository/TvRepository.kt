package com.ananda.movieapidb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ananda.movieapidb.api.ApiResponse
import com.ananda.movieapidb.api.TvService
import com.ananda.movieapidb.mappers.KeywordResponseMapper
import com.ananda.movieapidb.mappers.ReviewResponseMapper
import com.ananda.movieapidb.mappers.VideoResponseMapper
import com.ananda.movieapidb.models.Keyword
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.Review
import com.ananda.movieapidb.models.Video
import com.ananda.movieapidb.models.network.KeywordListResponse
import com.ananda.movieapidb.models.network.ReviewListResponse
import com.ananda.movieapidb.models.network.VideoListResponse
import com.ananda.movieapidb.room.TvDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    val service: TvService,
    val tvDao: TvDao
) : Repository {

    init {
        Timber.d("Injection TvRepository")
    }

    fun loadKeywordList(id: Int): LiveData<Resource<List<Keyword>>> {
        return object : NetworkBoundRepository<List<Keyword>, KeywordListResponse, KeywordResponseMapper>() {
            override fun saveFetchData(items: KeywordListResponse) {
                val tv = tvDao.getTv(id_ = id)
                tv.keywords = items.keywords
                tvDao.updateTv(tv = tv)
            }

            override fun shouldFetch(data: List<Keyword>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Keyword>> {
                val movie = tvDao.getTv(id_ = id)
                val data: MutableLiveData<List<Keyword>> = MutableLiveData()
                data.postValue(movie.keywords)
                return data
            }

            override fun fetchService(): LiveData<ApiResponse<KeywordListResponse>> {
                return service.fetchKeywords(id = id)
            }

            override fun mapper(): KeywordResponseMapper {
                return KeywordResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }

    fun loadVideoList(id: Int): LiveData<Resource<List<Video>>> {
        return object : NetworkBoundRepository<List<Video>, VideoListResponse, VideoResponseMapper>() {
            override fun saveFetchData(items: VideoListResponse) {
                val tv = tvDao.getTv(id_ = id)
                tv.videos = items.results
                tvDao.updateTv(tv = tv)
            }

            override fun shouldFetch(data: List<Video>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Video>> {
                val movie = tvDao.getTv(id_ = id)
                val data: MutableLiveData<List<Video>> = MutableLiveData()
                data.postValue(movie.videos)
                return data
            }

            override fun fetchService(): LiveData<ApiResponse<VideoListResponse>> {
                return service.fetchVideos(id = id)
            }

            override fun mapper(): VideoResponseMapper {
                return VideoResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }

    fun loadReviewsList(id: Int): LiveData<Resource<List<Review>>> {
        return object : NetworkBoundRepository<List<Review>, ReviewListResponse, ReviewResponseMapper>() {
            override fun saveFetchData(items: ReviewListResponse) {
                val tv = tvDao.getTv(id_ = id)
                tv.reviews = items.results
                tvDao.updateTv(tv = tv)
            }

            override fun shouldFetch(data: List<Review>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Review>> {
                val movie = tvDao.getTv(id_ = id)
                val data: MutableLiveData<List<Review>> = MutableLiveData()
                data.postValue(movie.reviews)
                return data
            }

            override fun fetchService(): LiveData<ApiResponse<ReviewListResponse>> {
                return service.fetchReviews(id = id)
            }

            override fun mapper(): ReviewResponseMapper {
                return ReviewResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }
}
