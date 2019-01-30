package com.enpassio.twowaydatabinding.data;

import android.arch.persistence.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ListConverter {

    @TypeConverter
    public static String convertListToJson(List<String> categories){
        Type categoryList = new TypeToken<List<String>>(){}.getType();
        return new Gson().toJson(categories, categoryList);
    }

    @TypeConverter
    public static List<String> convertJsonToList(String jsonToConvert){
        Type stepList = new TypeToken<List<String>>(){}.getType();
        return new Gson().fromJson(jsonToConvert, stepList);
    }

}
