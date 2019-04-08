package com.enpassio.twowaydatabinding.data.converters;

import android.arch.persistence.room.TypeConverter;

import com.enpassio.twowaydatabinding.data.model.Gender;

public class GenderTypeConverter {

    @TypeConverter
    public static int convertGenderToPosition(Gender gender){
        return gender == null ? 0 : gender.ordinal();
    }

    @TypeConverter
    public static Gender convertPositionToGender(int position){
        return Gender.values()[position];
    }
}
