package com.example.tranbesample.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categories implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("images")
    public ImagesEntity images;

    public int type;

    public List<Categories> childCategories;

    @Override
    public String toString() {
        return "Categories{" +
                "name='" + name + '\'' +
                ", images=" + images +
                ", childCategories=" + childCategories +
                '}';
    }
}
