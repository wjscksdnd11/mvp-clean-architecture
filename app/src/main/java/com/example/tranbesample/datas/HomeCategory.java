package com.example.tranbesample.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomeCategory implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("images")
    public ImagesEntity images;

    public int type;

    public List<HomeCategory> childCategories;

    @Override
    public String toString() {
        return "HomeCategory{" +
                "name='" + name + '\'' +
                ", images=" + images +
                ", childCategories=" + childCategories +
                '}';
    }
}
