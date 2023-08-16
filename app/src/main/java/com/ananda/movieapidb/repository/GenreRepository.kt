package com.ananda.movieapidb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ananda.movieapidb.api.ApiResponse
import com.ananda.movieapidb.api.GenreService
import com.ananda.movieapidb.mappers.GenreResponseMapper
import com.ananda.movieapidb.mappers.PeopleResponseMapper
import com.ananda.movieapidb.mappers.PersonDetailResponseMapper
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.entity.Genre
import com.ananda.movieapidb.models.entity.Person
import com.ananda.movieapidb.models.network.GenreResponse
import com.ananda.movieapidb.models.network.PeopleResponse
import com.ananda.movieapidb.models.network.PersonDetail
import com.ananda.movieapidb.room.GenreDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    val genreService: GenreService,
    val genreDao: GenreDao
) : Repository {

    init {
        Timber.d("Injection PeopleRepository")
    }

    fun loadGenre(page: Int): LiveData<Resource<List<Genre>>> {
        return object : NetworkBoundRepository<List<Genre>, GenreResponse, GenreResponseMapper>() {
            override fun saveFetchData(items: GenreResponse) {
                genreDao.insertGenre(items.genres)
            }

            override fun shouldFetch(data: List<Genre>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Genre>> {
                return genreDao.getGenre()
            }

            override fun fetchService(): LiveData<ApiResponse<GenreResponse>> {
                return genreService.fetchGenreList(page = page)
            }

            override fun mapper(): GenreResponseMapper {
                return GenreResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }
//
//    fun loadPersonDetail(id: Int): LiveData<Resource<PersonDetail>> {
//        return object : NetworkBoundRepository<PersonDetail, PersonDetail, PersonDetailResponseMapper>() {
//            override fun saveFetchData(items: PersonDetail) {
//                val person = genreDao.getGenre()
//                person.personDetail = items
//                genreDao.updatePerson(person = person)
//            }
//
//            override fun shouldFetch(data: PersonDetail?): Boolean {
//                return data == null || data.biography.isEmpty()
//            }
//
//            override fun loadFromDb(): LiveData<PersonDetail> {
//                val person = genreDao.getPerson(id_ = id)
//                val data: MutableLiveData<PersonDetail> = MutableLiveData()
//                data.postValue(person.personDetail)
//                return data
//            }
//
//            override fun fetchService(): LiveData<ApiResponse<PersonDetail>> {
//                return genreService.fetchPersonDetail(id = id)
//            }
//
//            override fun mapper(): PersonDetailResponseMapper {
//                return PersonDetailResponseMapper()
//            }
//
//            override fun onFetchFailed(message: String?) {
//                Timber.d("onFetchFailed : $message")
//            }
//        }.asLiveData()
    //}
}