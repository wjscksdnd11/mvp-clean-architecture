package com.example.tranbesample.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ContainerData implements Serializable {

    @SerializedName("categories")
    public List<HomeCategory> categoryList;
}
