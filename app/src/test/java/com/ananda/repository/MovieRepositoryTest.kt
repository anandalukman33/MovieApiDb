package com.ananda.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.ananda.api.ApiUtil.successCall
import com.ananda.movieapidb.api.MovieService
import com.ananda.movieapidb.models.Keyword
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.Review
import com.ananda.movieapidb.models.Video
import com.ananda.movieapidb.models.network.KeywordListResponse
import com.ananda.movieapidb.models.network.ReviewListResponse
import com.ananda.movieapidb.models.network.VideoListResponse
import com.ananda.movieapidb.repository.MovieRepository
import com.ananda.movieapidb.room.MovieDao
import com.ananda.utils.MockTestUtil.Companion.mockKeywordList
import com.ananda.utils.MockTestUtil.Companion.mockMovie
import com.ananda.utils.MockTestUtil.Companion.mockReviewList
import com.ananda.utils.MockTestUtil.Companion.mockVideoList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieRepositoryTest {
  private lateinit var repository: MovieRepository
  private val movieDao = mock<MovieDao>()
  private val service = mock<MovieService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    repository = MovieRepository(service, movieDao)
  }

  @Test
  fun loadKeywordListFromNetwork() {
    val loadFromDB = mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = KeywordListResponse(123, mockKeywordList())
    val call = successCall(mockResponse)
    whenever(service.fetchKeywords(123)).thenReturn(call)

    val data = repository.loadKeywordList(123)
    verify(movieDao).getMovie(123)
    verifyNoMoreInteractions(service)

    val observer = mock<Observer<Resource<List<Keyword>>>>()
    data.observeForever(observer)
    verify(observer).onChanged(Resource.success(mockKeywordList()))

    val updatedMovie = mockMovie()
    updatedMovie.keywords = mockKeywordList()
    verify(movieDao).updateMovie(updatedMovie)
  }

  @Test
  fun loadVideoListFromNetwork() {
    val loadFromDB = mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = VideoListResponse(123, mockVideoList())
    val call = successCall(mockResponse)
    whenever(service.fetchVideos(123)).thenReturn(call)

    val data = repository.loadVideoList(123)
    verify(movieDao).getMovie(123)
    verifyNoMoreInteractions(service)

    val observer = mock<Observer<Resource<List<Video>>>>()
    data.observeForever(observer)
    verify(observer).onChanged(Resource.success(mockVideoList()))

    val updatedMovie = mockMovie()
    updatedMovie.videos = mockVideoList()
    verify(movieDao).updateMovie(updatedMovie)
  }

  @Test
  fun loadReviewListFromNetwork() {
    val loadFromDB = mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = ReviewListResponse(123, 1, mockReviewList(), 100, 100)
    val call = successCall(mockResponse)
    whenever(service.fetchReviews(123)).thenReturn(call)

    val data = repository.loadReviewsList(123)
    verify(movieDao).getMovie(123)
    verifyNoMoreInteractions(service)

    val observer = mock<Observer<Resource<List<Review>>>>()
    data.observeForever(observer)
    verify(observer).onChanged(Resource.success(mockReviewList()))

    val updatedMovie = mockMovie()
    updatedMovie.reviews = mockReviewList()
    verify(movieDao).updateMovie(updatedMovie)
  }
}
