package com.example.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieCredit {
    @SerializedName("cast")
    @Expose
    private ArrayList<Cast> cast;

    public ArrayList<Cast> getCast() {
        return cast;
    }
}
