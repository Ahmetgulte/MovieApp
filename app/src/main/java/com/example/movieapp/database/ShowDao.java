package com.example.movieapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShowDao {
    @Query("SELECT * FROM watchlist ")
    LiveData<List<WatchListShow>> loadAllTasks();

    @Insert
    void insertTask(WatchListShow watchListShow);

    @Update
    void updateTask(WatchListShow watchListShow);

    @Delete
    void deleteTask(WatchListShow watchListShow);

    @Query("SELECT * FROM watchlist WHERE showId= :id")
    WatchListShow getShowById(int id);

}
