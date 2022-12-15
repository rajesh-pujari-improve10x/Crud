package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String title;
    public String description;
    public String seriesId;
    public String moviesId;
    public String imageUrl;

    public Movie() {
    }

    public Movie(String movieId, String seriesId, String movieName, String imageUrl, String description) {
        this.moviesId = movieId;
        this.seriesId = seriesId;
        this.title = movieName;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
