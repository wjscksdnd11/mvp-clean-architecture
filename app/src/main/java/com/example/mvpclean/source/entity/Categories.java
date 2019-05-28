package com.example.mvpclean.source.entity;

import android.graphics.Bitmap;
import android.os.Build;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * api 콜 이후 local Database에 저장.
 */
@Entity(tableName = "categories", indices = {@Index(value = {"caetgory_name", "caetgory_type"},
        unique = true)})
public class Categories {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entry_id")
    private String mId;



    @Nonnull
    @ColumnInfo(name = "caetgory_name")
    private final String mName;

    @Nonnull
    @ColumnInfo(name = "caetgory_type")
    private final int mCategoryType;


    @Nonnull
    @ColumnInfo(name = "low_url")
    private final String mLowUrl;


    @Nonnull
    @ColumnInfo(name = "mid_url")
    private final String mMidUrl;

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
    @NonNull
    public String getId() {
        return mId;
    }


    @Nonnull
    public String getName() {
        return mName;
    }

    public int getCategoryType() {
        return mCategoryType;
    }

    @Nonnull
    public String getLowUrl() {
        return mLowUrl;
    }

    @Nonnull
    public String getMidUrl() {
        return mMidUrl;
    }

    @Nonnull
    public String getHighurl() {
        return mHighurl;
    }

    public Bitmap getImages() {
        return images;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categories that = (Categories) o;
            return mCategoryType == that.mCategoryType &&
                    Objects.equals(mId, that.mId) &&
                Objects.equals(mName, that.mName);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
            return Objects.hash(mId, mName, mCategoryType);

    }

    public void setImages(Bitmap images) {
        this.images = images;
    }

    public void setId(@NonNull String mId) {
        this.mId = mId;
    }
}
