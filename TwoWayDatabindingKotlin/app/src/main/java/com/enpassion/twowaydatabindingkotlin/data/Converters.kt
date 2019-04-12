package com.enpassion.twowaydatabindingkotlin.data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MapConverter {

    @TypeConverter
    fun convertMapToJson(categories: Map<String, Boolean>): String {
        val categoryMap = object : TypeToken<Map<String, Boolean>>() {}.type
        return Gson().toJson(categories, categoryMap)
    }

    @TypeConverter
    fun convertJsonToMap(jsonToConvert: String): Map<String, Boolean> {
        val categoryMap = object : TypeToken<Map<String, Boolean>>() {}.type
        return Gson().fromJson(jsonToConvert, categoryMap)
    }
}

class GenderTypeConverter {

    @TypeConverter
    fun convertGenderToPosition(gender: Gender?): Int {
        return gender?.ordinal ?: 0
    }

    @TypeConverter
    fun convertPositionToGender(position: Int): Gender {
        return Gender.values()[position]
    }
}

class ProcurementTypeConverter {

    @TypeConverter
    fun convertProcurementTypeToInt(type: ProcurementType?): Int {
        return type?.ordinal ?: -1
    }

    @TypeConverter
    fun convertIntToProcurementType(enumOrdinal: Int): ProcurementType? {
        return if (enumOrdinal == -1) null else ProcurementType.values()[enumOrdinal]
    }
}