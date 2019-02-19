package com.enpassio.twowaydatabinding.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class ListConverter {

    @TypeConverter
    public static String convertListToJson(Map<String, Boolean> categories){
        Type categoryList = new TypeToken<Map<String, Boolean>>(){}.getType();
        return new Gson().toJson(categories, categoryList);
    }

    @TypeConverter
    public static Map<String, Boolean> convertJsonToList(String jsonToConvert){
        Type categoryList = new TypeToken<Map<String, Boolean>>(){}.getType();
        return new Gson().fromJson(jsonToConvert, categoryList);
    }

}
