package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends BaseActivity {

    private Series series;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText seriesImageUrlTxt;
    private CrudApi crudApi;
    private CrudService crudService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        log("onCreate Called");
        initView();
        setupApiMethods();
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            this.series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showSeries();
        } else {
            getSupportActionBar().setTitle("Add Series");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String seriesImageUrl = seriesImageUrlTxt.getText().toString();
            if (this.series == null) {
                addSeries(seriesId, seriesImageUrl, seriesName);
            } else {
                updateSeries(this.series.id, seriesId, seriesImageUrl, seriesName);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImageUrlTxt = findViewById(R.id.series_image_url_txt);
    }

    private void setupApiMethods() {
        crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void showSeries() {
        seriesIdTxt.setText(series.seriesId);
        seriesNameTxt.setText(series.title);
        seriesImageUrlTxt.setText(series.imageUrl);
    }

    private void addSeries(String seriesId, String imageUrl, String title) {
        this.series = new Series(seriesId, imageUrl, title);

        Call<Series> call = crudService.createSeriesList(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Successfully Added Series");
            }
        });
    }

    private void updateSeries(String id, String seriesId, String imageUrl, String title) {
        series = new Series(seriesId, imageUrl, title);

        Call<Void> call = crudService.updateSeries(id, series);
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