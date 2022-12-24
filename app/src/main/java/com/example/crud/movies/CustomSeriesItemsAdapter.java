package com.example.crud.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crud.R;
import com.example.crud.databinding.SeriesSpinnerItemBinding;
import com.example.crud.series.SeriesItem;

import java.util.List;

public class CustomSeriesItemsAdapter extends ArrayAdapter<SeriesItem> {

    private SeriesSpinnerItemBinding binding;

    public CustomSeriesItemsAdapter(@NonNull Context context, int resource, @NonNull List<SeriesItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        binding = SeriesSpinnerItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        SeriesItem series = getItem(position);
        binding.titleTxt.setText(series.seriesId + " - " + series.title);
        return binding.getRoot();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        binding = SeriesSpinnerItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        SeriesItem series = getItem(position);
        binding.titleTxt.setText(series.seriesId + " - " + series.title);
        return binding.getRoot();
    }
}
