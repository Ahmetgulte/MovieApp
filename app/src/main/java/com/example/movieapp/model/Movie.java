package com.example.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;

    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("runtime")
    @Expose
    private int runtime;

    @SerializedName("vote_average")
    @Expose
    private Number vote_average;




    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getVote_average() {
        return vote_average.toString();
    }
}
