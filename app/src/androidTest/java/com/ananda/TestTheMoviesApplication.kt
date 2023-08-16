package com.ananda

import com.ananda.movieapidb.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TestTheMoviesApplication : DaggerApplication() {

  private val appComponent = DaggerAppComponent.factory().create(this)

  override fun onCreate() {
    super.onCreate()
    appComponent.inject(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return appComponent
  }
}
