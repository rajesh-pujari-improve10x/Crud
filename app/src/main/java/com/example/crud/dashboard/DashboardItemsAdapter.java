package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.messages.MessagesActivity;
import com.example.crud.movies.MoviesActivity;
import com.example.crud.R;
import com.example.crud.series.SeriesItemsActivity;
import com.example.crud.templates.TemplatesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;

    void setData(ArrayList<DashboardItem> dashboardItems) {
        this.dashboardItems = dashboardItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        DashboardItemViewHolder dashboardItemViewHolder = new DashboardItemViewHolder(view);
        return dashboardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemViewHolder holder, int position) {
        DashboardItem dashboardItem = dashboardItems.get(position);
        Picasso.get().load(dashboardItem.imageUrl).into(holder.imageImg);
        holder.titleTxt.setText(dashboardItem.title);
        holder.itemView.setOnClickListener(view -> {
            if (holder.titleTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.itemView.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.itemView.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Series")) {
                Intent intent = new Intent(holder.itemView.getContext(), SeriesItemsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.itemView.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }
}
