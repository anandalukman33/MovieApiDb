package com.ananda.movieapidb

import android.os.Build
import com.ananda.movieapidb.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.DaggerApplication
import timber.log.Timber

@Suppress("unused")
class TheMoviesApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (!isRobolectricUnitTest()) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun isRobolectricUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }

    override fun applicationInjector() = appComponent
}