package com.enpassio.databindingwithrecyclerview.utils;

import java.text.NumberFormat;

public final class DatabindingUtils {

    //This method gets the price as an integer and adds a currency sign before it
    public static String addCurrencySign(int price){
       return NumberFormat.getCurrencyInstance().format(price);
    }
}
