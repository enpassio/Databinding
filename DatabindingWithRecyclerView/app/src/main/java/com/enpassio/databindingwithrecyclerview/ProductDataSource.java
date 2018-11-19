package com.enpassio.databindingwithrecyclerview;

import java.util.ArrayList;

public final class ProductDataSource {

    public static ArrayList<Product> getProductData() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product(1, "pencil", 5, R.drawable.pencil));
        productArrayList.add(new Product(2, "bottle", 150, R.drawable.bottle));
        productArrayList.add(new Product(3, "chair", 500, R.drawable.chair));
        productArrayList.add(new Product(4, "apple", 100, R.drawable.apple));
        productArrayList.add(new Product(5, "paper", 20, R.drawable.paper));
        productArrayList.add(new Product(6, "cup", 50, R.drawable.cup));
        productArrayList.add(new Product(7, "keyboard", 950, R.drawable.keyboard));
        productArrayList.add(new Product(8, "mobile", 2000, R.drawable.mobile));
        productArrayList.add(new Product(9, "bag", 500, R.drawable.bag));
        productArrayList.add(new Product(10, "bulb", 100, R.drawable.bulb));
        return productArrayList;
    }
}
