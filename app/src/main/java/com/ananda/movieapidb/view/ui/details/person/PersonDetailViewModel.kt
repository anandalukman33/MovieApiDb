package com.ananda.movieapidb.view.ui.details.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.network.PersonDetail
import com.ananda.movieapidb.repository.PeopleRepository
import com.ananda.movieapidb.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class PersonDetailViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

    private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val personLiveData: LiveData<Resource<PersonDetail>>

    init {
        Timber.d("Injection : PersonDetailViewModel")

        personLiveData = personIdLiveData.switchMap {
            personIdLiveData.value?.let {
                repository.loadPersonDetail(it)
            } ?: AbsentLiveData.create()
        }
    }

    fun postPersonId(id: Int) = personIdLiveData.postValue(id)
}