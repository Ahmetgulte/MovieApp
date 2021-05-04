package com.example.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episode {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
