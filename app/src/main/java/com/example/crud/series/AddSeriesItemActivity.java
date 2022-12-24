package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = binding.seriesIdTxt.getText().toString();
            String seriesName = binding.seriesNameTxt.getText().toString();
            String seriesImageUrl = binding.seriesImageUrlTxt.getText().toString();
            addSeriesItem(seriesId, seriesName, seriesImageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeriesItem(String seriesId, String title, String imageUrl) {
        SeriesItem seriesItem = new SeriesItem(seriesId, title, imageUrl);
        Call<SeriesItem> call = crudService.createSeriesItem(seriesItem);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                showToast("Failed Added Series");
            }
        });
    }
}
