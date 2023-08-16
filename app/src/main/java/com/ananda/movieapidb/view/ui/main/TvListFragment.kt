package com.ananda.movieapidb.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ananda.movieapidb.R
import com.ananda.movieapidb.compose.ViewModelFragment
import com.ananda.movieapidb.databinding.MainFragmentTvBinding
import com.ananda.movieapidb.view.adapter.TvListAdapter

class TvListFragment : ViewModelFragment() {

    private val viewModel: MainActivityViewModel by injectActivityVIewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<MainFragmentTvBinding>(inflater, R.layout.main_fragment_tv, container)
            .apply {
                viewModel = this@TvListFragment.viewModel.apply { postTvPage(1) }
                lifecycleOwner = this@TvListFragment
                adapter = TvListAdapter()
            }.root
    }
}