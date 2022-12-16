package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {

    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_items);
        getSupportActionBar().setTitle("Series");
        initView();
        setupSeriesItemsAdapter();
        setupSeriesItemsRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_items_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesItemActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSeriesItems();
    }

    private void initView() {
        //Todo: change the id name series_items_rv
        seriesItemsRv = findViewById(R.id.series_rv);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void setupSeriesItemsRv() {
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesItemsRv.setAdapter(seriesItemsAdapter);
    }

    private void setupSeriesItemsAdapter() {
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesItems);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeriesItem(id);
            }

            @Override
            public void onEdit(SeriesItem seriesItem) {
                editSeries(seriesItem);
                showToast("Successfully Edit Series");
            }
        });
    }

    private void fetchSeriesItems() {
        showProgressBar();
        Call<List<SeriesItem>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                hideProgressBar();
                List<SeriesItem> seriesItems = response.body();
                seriesItemsAdapter.setData(seriesItems);
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load Data");
            }
        });
    }

    private void deleteSeriesItem(String id) {
        showProgressBar();
        Call<Void> call = crudService.deleteSeriesItem(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideProgressBar();
                fetchSeriesItems();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideProgressBar();
                showToast("Failed delete series");
            }
        });
    }

    private void editSeries(SeriesItem seriesItem) {
        Intent intent = new Intent(this, EditSeriesItemActivity.class);
        intent.putExtra(Constants.KEY_SERIES_ITEM, seriesItem);
        startActivity(intent);
    }
}