package com.example.tranbesample.datas;

import com.example.tranbesample.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomeCategory implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("images")
    public ImagesData images;

    public Constants.CATEGORY type;

    @SerializedName("childCategories")
    public List<HomeCategory> childCategories;

    public boolean isOpen = false;

    @Override
    public String toString() {
        return "HomeCategory{" +
                "name='" + name + '\'' +
                ", images=" + images +
                ", childCategories=" + childCategories +
                '}';
    }

    public String getName() {
        return name;
    }

    public ImagesData getImages() {
        return images;
    }

    public Constants.CATEGORY getType() {
        return type;
    }

    public List<HomeCategory> getChildCategories() {
        return childCategories;
    }
}
