package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class DashBoardViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout dashBoardLL;
    public ImageView imageImg;
    public TextView titleTxt;

    public DashBoardViewHolder(@NonNull View itemView) {
        super(itemView);
        dashBoardLL = itemView.findViewById(R.id.dash_board_ll);
        imageImg = itemView.findViewById(R.id.image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
