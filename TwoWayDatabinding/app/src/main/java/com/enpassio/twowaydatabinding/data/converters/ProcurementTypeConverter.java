package com.enpassio.twowaydatabinding.data.converters;

import android.arch.persistence.room.TypeConverter;

import com.enpassio.twowaydatabinding.data.model.ProcurementType;

public class ProcurementTypeConverter {

    @TypeConverter
    public static int convertProcurementTypeToInt(ProcurementType type){
        return type == null ? -1 : type.ordinal();
    }

    @TypeConverter
    public static ProcurementType convertIntToProcurementType(int enumOrdinal){
        if(enumOrdinal == -1){
            return null;
        } else {
            return ProcurementType.values()[enumOrdinal];
        }
    }
}
