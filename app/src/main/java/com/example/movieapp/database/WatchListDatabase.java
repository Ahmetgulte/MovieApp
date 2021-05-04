package com.example.movieapp.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {WatchListShow.class}, version = 2, exportSchema = false)
public abstract class WatchListDatabase extends RoomDatabase {
    private static final String LOG_TAG =WatchListDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME ="watchlater";
    private static WatchListDatabase sInstance;

    public static WatchListDatabase getInstance(Context context){
        if(sInstance ==null){
            synchronized (LOCK){
                Log.d(LOG_TAG,"Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        WatchListDatabase.class,WatchListDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG,"Getting the database instance");
        return sInstance;
    }
    public abstract ShowDao showDao();


}
