package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {

    public Series series;
    public EditText seriesIdTxt;
    public EditText seriesNameTxt;
    public EditText seriesImageUrlTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        initView();
        if (getIntent().hasExtra("Series")) {
            getSupportActionBar().setTitle("Add Series");
            this.series = (Series) getIntent().getSerializableExtra("Series");
        } else {

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
                updateSeries(seriesId, seriesName, seriesImageUrl);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void initView() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImageUrlTxt = findViewById(R.id.series_image_url_txt);
    }

    public void addSeries(String id, String imageUrl, String title) {
        this.series = new Series(id, imageUrl, title);

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Series> call = crudService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully Added Series", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateSeries(String id, String imageUrl, String title) {
        series = new Series(id, imageUrl, title);

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.updateSeries(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed Edit Series", Toast.LENGTH_SHORT).show();
            }
        });
    }
}