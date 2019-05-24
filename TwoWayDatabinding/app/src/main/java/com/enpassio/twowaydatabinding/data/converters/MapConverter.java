package com.enpassio.twowaydatabinding.data.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class MapConverter {

    @TypeConverter
    public static String convertMapToJson(Map<String, Boolean> categories){
        Type categoryMap = new TypeToken<Map<String, Boolean>>(){}.getType();
        return new Gson().toJson(categories, categoryMap);
    }

    @TypeConverter
    public static Map<String, Boolean> convertJsonToMap(String jsonToConvert){
        Type categoryMap = new TypeToken<Map<String, Boolean>>(){}.getType();
        return new Gson().fromJson(jsonToConvert, categoryMap);
    }

}
