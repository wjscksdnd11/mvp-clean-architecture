package com.example.mvpclean.source.local;


import com.example.mvpclean.source.entity.Categories;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoryDao {
    /**
     * Select all categories from the categories table.
     */
    @Query("SELECT * FROM Categories")
    List<Categories> getCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Categories category);

    @Query("DELETE FROM CATEGORIES")
    void deleteAllCategories();

}
