package com.enpassio.databindingwithrecyclerview.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private final String productName;
    private final int productPrice;
    private final int productImage;

    public Product(String name, int price, int imageId){
        productName = name;
        productPrice = price;
        productImage = imageId;
    }

    //Databinding need this getters to get the fields we declared in xml layout
    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    /*Implementing Parcelable is needed for passing the Product object from
    ProductListFragment to DetailsFragment. This is not related to Databinding. */
    private Product(Parcel in) {
        productName = in.readString();
        productPrice = in.readInt();
        productImage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeInt(productPrice);
        dest.writeInt(productImage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
