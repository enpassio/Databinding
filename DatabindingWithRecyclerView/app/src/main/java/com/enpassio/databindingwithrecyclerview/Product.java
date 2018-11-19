package com.enpassio.databindingwithrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int productId;
    private String productName;
    private int productPrice;
    private int productImage;

    Product(int id, String name, int price, int imageId){
        productId = id;
        productName = name;
        productPrice = price;
        productImage = imageId;
    }

    private Product(Parcel in) {
        productId = in.readInt();
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
        dest.writeInt(productId);
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
