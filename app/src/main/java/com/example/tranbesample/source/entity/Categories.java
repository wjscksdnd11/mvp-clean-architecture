package com.example.tranbesample.source.entity;

import android.graphics.Bitmap;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories", indices = {@Index(value = {"caetgory_name", "caetgory_type"},
        unique = true)})
public class Categories {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entry_id")
    private final String mId;


    @Nonnull
    @ColumnInfo(name = "caetgory_name")
    private final String mName;

    @Nonnull
    @ColumnInfo(name = "caetgory_type")
    private final int mCategoryType;


    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "low_url")
    private final String mLowUrl;


    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "mid_url")
    private final String mMidUrl;

    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "high_url")
    private final String mHighurl;

    @Ignore
    Bitmap images;

    public Categories(@Nonnull String mName,  int mCategoryType, @Nonnull String mLowUrl, @Nonnull String mMidUrl, @Nonnull String mHighurl) {
        this.mId = UUID.randomUUID().toString();
        this.mName = mName;
        this.mCategoryType = mCategoryType;
        this.mLowUrl = mLowUrl;
        this.mMidUrl = mMidUrl;
        this.mHighurl = mHighurl;
    }
}
