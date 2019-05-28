package com.example.mvpclean.source;

import android.content.Context;

import com.example.mvpclean.source.entity.Categories;
import com.example.mvpclean.source.local.CategoryDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Categories.class}, version =1,exportSchema = false )
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract CategoryDao categoryDao();
    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context){
        synchronized (sLock){
            if (INSTANCE ==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                , AppDatabase.class,"sample.db").build();
            }
            return INSTANCE;
        }
    }
}
