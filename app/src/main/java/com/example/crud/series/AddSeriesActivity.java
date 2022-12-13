package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesActivity extends BaseAddEditSeriesActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String seriesImageUrl = seriesImageUrlTxt.getText().toString();
            addSeries(seriesId, seriesImageUrl, seriesName);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeries(String seriesId, String imageUrl, String title) {
        Series series = new Series(seriesId, imageUrl, title);

        Call<Series> call = crudService.createSeriesList(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Failed Added Series");
            }
        });
    }
}
