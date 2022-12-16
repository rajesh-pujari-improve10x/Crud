package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesViewHolder> {
    //Todo: already given the comment
    private List<SeriesItem> seriesList;
    private OnItemActionListener onItemActionListener;

    void setData(List<SeriesItem> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener actionListener) {
        this.onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        SeriesItem seriesItem = seriesList.get(position);
        if (seriesItem.imageUrl != null && seriesItem.imageUrl.isEmpty() == false) {
            Picasso.get().load(seriesItem.imageUrl).into(holder.seriesItemImg);
        }
        holder.titleTxt.setText(seriesItem.title);
        holder.seriesDeleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(seriesItem.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(seriesItem);
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
