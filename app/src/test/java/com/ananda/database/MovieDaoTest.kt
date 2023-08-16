package com.ananda.database

import android.os.Looper
import com.ananda.movieapidb.models.entity.Movie
import com.ananda.utils.LiveDataTestUtil
import com.ananda.utils.MockTestUtil.Companion.mockMovie
import com.ananda.utils.MockTestUtil.Companion.mockMovieList
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
@LooperMode(LooperMode.Mode.PAUSED)
class MovieDaoTest : LocalDatabase() {

  @Test
  fun insertAndReadTest() {
    db.movieDao().insertMovieList(mockMovieList())
    val loadFromDB = LiveDataTestUtil.getValue(db.movieDao().getMovieList(1))[0]

    shadowOf(Looper.getMainLooper()).idle()

    assertThat(loadFromDB.page, `is`(1))
    assertThat(loadFromDB.id, `is`(123))
  }

  @Test
  fun updateAndReadTest() {
    val movieList = ArrayList<Movie>()
    val movie = mockMovie()
    movieList.add(movie)
    db.movieDao().insertMovieList(movieList)
    shadowOf(Looper.getMainLooper()).idle()
    val loadFromDB = db.movieDao().getMovie(movie.id)
    assertThat(loadFromDB.page, `is`(1))

    movie.page = 10
    db.movieDao().updateMovie(movie)

    val updated = db.movieDao().getMovie(movie.id)
    assertThat(updated.page, `is`(10))
  }
}
