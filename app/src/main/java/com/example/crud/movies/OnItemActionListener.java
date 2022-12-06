package com.example.crud.movies;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Movie movie);
}
