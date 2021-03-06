package com.example.mvpclean.datas;

import com.example.mvpclean.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * 메인 화면 Home Fragment Category Data
 */
public class HomeCategory implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("images")
    public ImagesData images;

    public Constants.CATEGORY type;

    @SerializedName("childCategories")
    public List<HomeCategory> childCategories;

    public boolean isOpen = false;

    @NonNull
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
