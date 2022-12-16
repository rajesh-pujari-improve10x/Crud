package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesItem implements Serializable {

    @SerializedName("_id")
    public String id;
    @SerializedName("id")
    public String seriesId;
    public String imageUrl;
    public String title;

    public SeriesItem() {
    }

    public SeriesItem(String seriesId, String title, String imageUrl) {
        this.seriesId = seriesId;
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
