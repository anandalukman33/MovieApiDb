package com.ananda.movieapidb.di

import com.ananda.movieapidb.di.annotations.FragmentScope
import com.ananda.movieapidb.view.ui.main.GenreListFragment
import com.ananda.movieapidb.view.ui.main.MovieListFragment
import com.ananda.movieapidb.view.ui.main.PersonListFragment
import com.ananda.movieapidb.view.ui.main.TvListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeTvListFragment(): TvListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributePersonListFragment(): PersonListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeGenreListFragment(): GenreListFragment
}