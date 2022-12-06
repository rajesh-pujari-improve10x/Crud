package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Series implements Serializable {

    @SerializedName("_id")
    public String id;
    @SerializedName("id")
    public String seriesId;
    public String imageUrl;
    public String title;

    public Series() {
    }

    public Series(String seriesId, String imageUrl, String title) {
        this.seriesId = seriesId;
        this.imageUrl = imageUrl;
        this.title = title;
    }
}
