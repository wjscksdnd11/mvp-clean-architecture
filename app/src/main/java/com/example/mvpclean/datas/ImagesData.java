package com.example.mvpclean.datas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;

/**
 * Java의 변수명이 숫자가 앞에 올 수 없기때문에, 이에 유리한 Retrofit Anotation을 활용하였습니다.
 *
 */
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

    @NonNull
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
