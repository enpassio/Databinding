package com.enpassio.twowaydatabinding.utils;

import android.databinding.InverseMethod;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.data.model.Gender;
import com.enpassio.twowaydatabinding.data.model.ProcurementType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class BindingUtils {

    public static String attachCategories(Map<String,Boolean> categories){
        if(categories == null){
            return null;
        }

        //Filter categories whose values are true
        ArrayList<String> filteredList = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : categories.entrySet()) {
            if(entry.getValue()) {
                filteredList.add(entry.getKey());
            }
        }

        //Append categories in the list with comma as separator
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = filteredList.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            sb.append(element);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @InverseMethod("buttonIdToProcurementType")
    public static int procurementTypeToButtonId(ProcurementType procurementType ){
        int selectedButtonId = -1;

        if(procurementType == null){
            return selectedButtonId;
        }

        switch (procurementType){
            case BOUGHT: {
                selectedButtonId = R.id.radioBtn_bought;
                break;
            }
            case RECEIVED: {
                selectedButtonId = R.id.radioBtn_received;
                break;
            }
        }
        return selectedButtonId;
    }

    public static ProcurementType buttonIdToProcurementType(int selectedButtonId){
        ProcurementType procurementType = null;
        switch (selectedButtonId){
            case R.id.radioBtn_bought: {
                procurementType = ProcurementType.BOUGHT;
                break;
            }
            case R.id.radioBtn_received: {
                procurementType = ProcurementType.RECEIVED;
            }
        }
        return procurementType;
    }

    @InverseMethod("positionToGender")
    public static int genderToPosition(Gender gender){
        return gender == null ? 0 : gender.ordinal();
    }

    public static Gender positionToGender(int position){
        return Gender.values()[position];
    }
}
