package com.enpassion.twowaydatabindingkotlin.data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object MapConverter {

    @TypeConverter
    fun convertMapToJson(categories: Map<String, Boolean>): String {
        val categoryMap = object : TypeToken<Map<String, Boolean>>() {

        }.type
        return Gson().toJson(categories, categoryMap)
    }

    @TypeConverter
    fun convertJsonToMap(jsonToConvert: String): Map<String, Boolean> {
        val categoryMap = object : TypeToken<Map<String, Boolean>>() {

        }.type
        return Gson().fromJson(jsonToConvert, categoryMap)
    }

}