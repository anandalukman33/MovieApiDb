package com.ananda.movieapidb.room

//import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveData
import androidx.room.*
import com.ananda.movieapidb.models.entity.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: List<Genre>)

//    @Update
//    fun updatePerson(genre: Genre)
//
//    @Query("SELECT * FROM people WHERE id = :id_")
//    fun getPerson(id_: Int): Genre
//
    @Query("SELECT * FROM Genre")
    fun getGenre(): LiveData<List<Genre>>
}