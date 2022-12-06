package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("_id")
    public String id;
    public String description;
    public int seriesId;
    public int moviesId;
    @SerializedName("name")
    public String title;
    public String imageUrl;
}
