package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.messages.MessagesActivity;
import com.example.crud.MoviesActivity;
import com.example.crud.R;
import com.example.crud.series.SeriesActivity;
import com.example.crud.templates.TemplatesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashBoardsAdapter extends RecyclerView.Adapter<DashBoardViewHolder> {

    public ArrayList<Dashboard> dashBoards;

    public void setData(ArrayList<Dashboard> dashBoards) {
        this.dashBoards = dashBoards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_board_item, parent, false);
        DashBoardViewHolder dashBoardViewHolder = new DashBoardViewHolder(view);
        return dashBoardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardViewHolder holder, int position) {
        Dashboard dashBoard = dashBoards.get(position);
        Picasso.get().load(dashBoard.imageUrl).into(holder.imageImg);
        holder.titleTxt.setText(dashBoard.title);
        holder.itemView.setOnClickListener(view -> {
            if (holder.titleTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.itemView.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.itemView.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Series")) {
                Intent intent = new Intent(holder.itemView.getContext(), SeriesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.itemView.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashBoards.size();
    }
}
