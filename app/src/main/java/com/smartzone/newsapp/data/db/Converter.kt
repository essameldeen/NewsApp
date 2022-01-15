package com.smartzone.newsapp.data.db

import androidx.room.TypeConverter
import com.smartzone.newsapp.data.model.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source): String {

        return source.name
    }

    @TypeConverter
    fun toSource(value: String): Source {

        return Source(value, value)
    }
}