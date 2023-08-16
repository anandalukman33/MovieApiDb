package com.ananda.movieapidb.di

import com.ananda.movieapidb.compose.ViewModelActivity
import com.ananda.movieapidb.compose.ViewModelFragment
import com.ananda.movieapidb.di.annotations.ActivityScope
import com.ananda.movieapidb.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComposeModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeViewModelActivity(): ViewModelActivity

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeViewModelFragment(): ViewModelFragment
}