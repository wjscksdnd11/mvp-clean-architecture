package com.example.tranbesample.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImagesData implements Serializable {

    @SerializedName("3x")
    private String high;

    @SerializedName("2x")
    private String mid;

    @SerializedName("1x")
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
        return "ImagesData{" +
                "high='" + high + '\'' +
                ", mid='" + mid + '\'' +
                ", low='" + low + '\'' +
                ", bestImg='" + bestImg + '\'' +
                '}';
    }
}
