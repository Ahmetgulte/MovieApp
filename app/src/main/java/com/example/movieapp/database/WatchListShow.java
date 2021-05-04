package com.example.movieapp.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "watchlist")
public class WatchListShow {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    protected int id;

    private String showId;
    private String name;
    private String poster;
    private String description;

    public WatchListShow(String showId,String name, String poster,String description) {
        this.showId = showId;
        this.name = name;
        this.poster = poster;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public String getShowId() {
        return showId;
    }

    public String getDescription() {
        return description;
    }
}
