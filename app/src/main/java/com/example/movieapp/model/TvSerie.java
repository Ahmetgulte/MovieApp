package com.example.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvSerie {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("poster_path")
    @Expose
    private String poster;

    @SerializedName("genre_ids")
    @Expose
    private ArrayList<Number> genres;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("vote_average")
    @Expose
    private Number vote_average;

    @SerializedName("seasons")
    @Expose
    private ArrayList<Season> seasons;

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public ArrayList<Number> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }


    public Number getVote_average() {
        return vote_average;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public int getId() {
        return id;
    }
}
