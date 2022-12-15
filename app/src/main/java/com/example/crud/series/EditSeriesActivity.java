package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesActivity extends BaseAddEditSeriesActivity{

    private Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showSeries();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String seriesImageUrl = seriesImageUrlTxt.getText().toString();
            updateSeries(series.id, seriesId, seriesName, seriesImageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showSeries() {
        seriesIdTxt.setText(series.seriesId);
        seriesNameTxt.setText(series.title);
        seriesImageUrlTxt.setText(series.imageUrl);
    }

    private void updateSeries(String id, String seriesId, String title, String imageUrl) {
        Series updatedSeries = new Series(seriesId, title, imageUrl);
        Call<Void> call = crudService.updateSeries(id, updatedSeries);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed Edit Series");
            }
        });
    }
}
