package com.example.mvpclean.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Json구조의 Container
 */
public class ContainerData implements Serializable {

    @SerializedName("categories")
    public List<HomeCategory> categoryList;
}
