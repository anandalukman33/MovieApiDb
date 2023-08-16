package com.ananda.movieapidb.di

import com.ananda.movieapidb.di.annotations.ActivityScope
import com.ananda.movieapidb.view.ui.details.movie.MovieDetailActivity
import com.ananda.movieapidb.view.ui.details.person.PersonDetailActivity
import com.ananda.movieapidb.view.ui.details.tv.TvDetailActivity
import com.ananda.movieapidb.view.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailActivity(): MovieDetailActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeTvDetailActivity(): TvDetailActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributePersonDetailActivity(): PersonDetailActivity
}