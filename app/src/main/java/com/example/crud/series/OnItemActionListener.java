package com.example.crud.series;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(SeriesItem seriesItem);
}
