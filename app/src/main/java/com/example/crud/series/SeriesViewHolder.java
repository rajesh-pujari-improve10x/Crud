package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    ImageView seriesItemImg;
    TextView titleTxt;
    ImageButton seriesDeleteBtn;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesItemImg = itemView.findViewById(R.id.series_item_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        seriesDeleteBtn = itemView.findViewById(R.id.series_delete_btn);
    }
}
