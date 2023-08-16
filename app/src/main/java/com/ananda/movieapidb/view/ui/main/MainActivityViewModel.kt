package com.ananda.movieapidb.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.entity.Movie
import com.ananda.movieapidb.models.entity.Person
import com.ananda.movieapidb.models.entity.Tv
import com.ananda.movieapidb.repository.DiscoverRepository
import com.ananda.movieapidb.repository.PeopleRepository
import com.ananda.movieapidb.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val discoverRepository: DiscoverRepository,
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
    val movieListLiveData: LiveData<Resource<List<Movie>>>

    private var tvPageLiveData: MutableLiveData<Int> = MutableLiveData()
    val tvListLiveData: LiveData<Resource<List<Tv>>>

    private var peoplePageLiveData: MutableLiveData<Int> = MutableLiveData()
    val peopleLiveData: LiveData<Resource<List<Person>>>

    init {
        Timber.d("injection MainActivityViewModel")

        movieListLiveData = moviePageLiveData.switchMap {
            moviePageLiveData.value?.let { discoverRepository.loadMovies(it) }
                ?: AbsentLiveData.create()
        }

        tvListLiveData = tvPageLiveData.switchMap {
            tvPageLiveData.value?.let { discoverRepository.loadTvs(it) } ?: AbsentLiveData.create()
        }

        peopleLiveData = peoplePageLiveData.switchMap {
            peoplePageLiveData.value?.let { peopleRepository.loadPeople(it) }
                ?: AbsentLiveData.create()
        }
    }

    fun getMovieListValues() = movieListLiveData.value
    fun postMoviePage(page: Int) = moviePageLiveData.postValue(page)

    fun getTvListValues() = tvListLiveData.value
    fun postTvPage(page: Int) = tvPageLiveData.postValue(page)

    fun getPeopleValues() = peopleLiveData.value
    fun postPeoplePage(page: Int) = peoplePageLiveData.postValue(page)
}