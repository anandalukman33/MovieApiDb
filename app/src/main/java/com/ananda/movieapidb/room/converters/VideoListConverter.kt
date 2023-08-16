package com.ananda.movieapidb.room.converters

import androidx.room.TypeConverter
import com.ananda.movieapidb.models.Video
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class VideoListConverter {
    @TypeConverter
    fun fromString(value: String): List<Video>? {
        val listType = object : TypeToken<List<Video>>() {}.type
        return Gson().fromJson<List<Video>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Video>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}