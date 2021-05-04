package com.example.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//popular
public class MovieResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
