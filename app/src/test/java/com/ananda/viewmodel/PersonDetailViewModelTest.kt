package com.ananda.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ananda.api.ApiUtil
import com.ananda.movieapidb.api.PeopleService
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.network.PersonDetail
import com.ananda.movieapidb.repository.PeopleRepository
import com.ananda.movieapidb.room.PeopleDao
import com.ananda.movieapidb.view.ui.details.person.PersonDetailViewModel
import com.ananda.utils.MockTestUtil
import com.ananda.utils.MockTestUtil.Companion.mockPerson
import com.ananda.utils.MockTestUtil.Companion.mockPersonDetail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PersonDetailViewModelTest {

  private lateinit var viewModel: PersonDetailViewModel

  private lateinit var repository: PeopleRepository
  private val peopleDao = mock<PeopleDao>()
  private val service = mock<PeopleService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    repository = PeopleRepository(service, peopleDao)
    viewModel = PersonDetailViewModel(repository)
  }

  @Test
  fun loadPersonDetail() {
    val loadFromDB = mockPerson()
    whenever(peopleDao.getPerson(123)).thenReturn(loadFromDB)

    val mockResponse = mockPersonDetail()
    val call = ApiUtil.successCall(mockResponse)
    whenever(service.fetchPersonDetail(123)).thenReturn(call)

    val data = repository.loadPersonDetail(123)
    val observer = mock<Observer<Resource<PersonDetail>>>()
    data.observeForever(observer)

    viewModel.postPersonId(123)
    verify(peopleDao, times(3)).getPerson(123)
    verify(observer).onChanged(
      Resource.success(MockTestUtil.mockPersonDetail()))

    val updatedPerson = MockTestUtil.mockPerson()
    updatedPerson.personDetail = MockTestUtil.mockPersonDetail()
    verify(peopleDao).updatePerson(updatedPerson)
  }
}
