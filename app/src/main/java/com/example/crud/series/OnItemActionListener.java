package com.example.crud.series;

import com.example.crud.series.Series;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Series series);
}
