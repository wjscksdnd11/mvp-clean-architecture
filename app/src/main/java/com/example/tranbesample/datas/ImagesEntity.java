package com.example.tranbesample.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImagesEntity implements Serializable {

    @SerializedName("x3")
    private String high;

    @SerializedName("x2")
    private String mid;

    @SerializedName("x1")
    private String low;

    private String bestImg;

    public String getHigh() {
        return high;
    }

    public String getMid() {
        return mid;
    }

    public String getLow() {
        return low;
    }

    @Override
    public String toString() {
        return "ImagesEntity{" +
                "high='" + high + '\'' +
                ", mid='" + mid + '\'' +
                ", low='" + low + '\'' +
                ", bestImg='" + bestImg + '\'' +
                '}';
    }
}
