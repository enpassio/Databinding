package com.enpassio.databindingwithrecyclerview.utils;

import com.enpassio.databindingwithrecyclerview.R;
import com.enpassio.databindingwithrecyclerview.model.Product;

import java.util.ArrayList;

public final class ProductDataSource {

    public static ArrayList<Product> getProductData() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product("pencil", 5, R.drawable.pencil));
        productArrayList.add(new Product("bottle", 150, R.drawable.bottle));
        productArrayList.add(new Product("chair", 500, R.drawable.chair));
        productArrayList.add(new Product("apple", 100, R.drawable.apple));
        productArrayList.add(new Product("paper", 20, R.drawable.paper));
        productArrayList.add(new Product("cup", 50, R.drawable.cup));
        productArrayList.add(new Product("keyboard", 950, R.drawable.keyboard));
        productArrayList.add(new Product("mobile", 2000, R.drawable.mobile));
        productArrayList.add(new Product("bag", 500, R.drawable.bag));
        productArrayList.add(new Product("bulb", 100, R.drawable.bulb));
        return productArrayList;
    }
}
