package com.example.mvpclean.source;

import android.content.Context;

import com.example.mvpclean.source.entity.Categories;
import com.example.mvpclean.source.local.CategoryDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Categories.class}, version =1,exportSchema = false )
public abstract class TrenbeDatabase extends RoomDatabase {
    private static TrenbeDatabase INSTANCE;
    public abstract CategoryDao categoryDao();
    private static final Object sLock = new Object();

    public static TrenbeDatabase getInstance(Context context){
        synchronized (sLock){
            if (INSTANCE ==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                ,TrenbeDatabase.class,"trenbe.db").build();
            }
            return INSTANCE;
        }
    }
}
