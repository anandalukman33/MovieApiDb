package com.ananda.movieapidb.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Genre")
data class Genre(
    @PrimaryKey val id: Int,
    var name: String
) : Parcelable