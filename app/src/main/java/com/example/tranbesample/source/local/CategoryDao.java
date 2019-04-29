package com.example.tranbesample.source.local;


import com.example.tranbesample.datas.HomeCategory;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoryDao {
    /**
     * Select all categories from the categories table.
     */
    @Query("SELECT * FROM Categories")
    List<HomeCategory> getCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(HomeCategory category);

    @Query("DELETE FROM CATEGORIES")
    void deleteAllCategories();

}
