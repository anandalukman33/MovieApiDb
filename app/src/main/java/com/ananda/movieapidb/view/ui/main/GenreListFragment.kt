package com.ananda.movieapidb.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ananda.movieapidb.R
import com.ananda.movieapidb.compose.ViewModelFragment
import com.ananda.movieapidb.databinding.MainFragmentGenreBinding
import com.ananda.movieapidb.view.adapter.GenreListAdapter

class GenreListFragment : ViewModelFragment() {

    private val viewModel: MainActivityViewModel by injectActivityVIewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<MainFragmentGenreBinding>(inflater, R.layout.main_fragment_genre, container)
            .apply {
                viewModel = this@GenreListFragment.viewModel.apply { postGenrePage(1) }
                lifecycleOwner = this@GenreListFragment
                adapter = GenreListAdapter()
            }.root
    }
}