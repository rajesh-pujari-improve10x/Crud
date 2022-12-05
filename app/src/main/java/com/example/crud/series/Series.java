package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

public class Series {

    @SerializedName("_id")
    public String id;
    @SerializedName("id")
    public String seriesId;
    public String imageUrl;
    public String title;

    public Series() {
    }

    public Series(String id, String imageUrl, String title) {
        this.seriesId = id;
        this.imageUrl = imageUrl;
        this.title = title;
    }
}
