package com.ananda.movieapidb.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ananda.movieapidb.R
import com.ananda.movieapidb.compose.ViewModelFragment
import com.ananda.movieapidb.databinding.MainFragmentStarBinding
import com.ananda.movieapidb.view.adapter.PeopleAdapter

class PersonListFragment : ViewModelFragment() {

    private val viewModel: MainActivityViewModel by injectActivityVIewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<MainFragmentStarBinding>(inflater, R.layout.main_fragment_star, container)
            .apply {
                viewModel = this@PersonListFragment.viewModel.apply { postPeoplePage(1) }
                lifecycleOwner = this@PersonListFragment
                adapter = PeopleAdapter()
            }.root
    }
}