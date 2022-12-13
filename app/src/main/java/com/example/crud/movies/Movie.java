package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    public String description;
    public String seriesId;
    public String moviesId;
    @SerializedName("name")
    public String title;
    public String imageUrl;

    public Movie() {
    }

    public Movie(String movieId, String movieName, String seriesId, String imageUrl, String description) {
        this.moviesId = movieId;
        this.title = movieName;
        this.seriesId = seriesId;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
