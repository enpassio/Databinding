package com.enpassio.databindingwithrecyclerview;

import java.text.NumberFormat;

public final class DatabindingUtils {

    public static String addCurrencySign(int price){
       return NumberFormat.getCurrencyInstance().format(price);
    }
}
