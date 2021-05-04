package com.example.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Season {
    @SerializedName("season_number")
    @Expose
    private String season_number;
    @SerializedName("episodes")
    @Expose
    private ArrayList<Episode> episodes;
    @SerializedName("name")
    @Expose
    private String name;

    public String getSeason_number() {
        return season_number;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
