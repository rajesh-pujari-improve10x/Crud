package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_SERIES_ITEM)) {
            getSupportActionBar().setTitle("Edit Series");
            seriesItem = (SeriesItem) getIntent().getSerializableExtra(Constants.KEY_SERIES_ITEM);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String seriesImageUrl = seriesImageUrlTxt.getText().toString();
            updateSeriesItem(seriesItem.id, seriesId, seriesName, seriesImageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        seriesImageUrlTxt.setText(seriesItem.imageUrl);
    }

    private void updateSeriesItem(String id, String seriesId, String title, String imageUrl) {
        SeriesItem updatedSeriesItem = new SeriesItem(seriesId, title, imageUrl);
        Call<Void> call = crudService.updateSeriesItem(id, updatedSeriesItem);
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
