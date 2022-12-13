package com.example.crud.movies;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected EditText movieIdTxt;
    protected EditText movieNameTxt;
    protected Spinner seriesSp;
    protected EditText imageUrlTxt;
    protected EditText descriptionTxt;
    protected CrudService crudService;
    protected CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        log("onCreate Called");
        initViews();
        setupApiMethods();
        fetchSeriesList();
        setupSeriesListSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    private void initViews() {
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesSp = findViewById(R.id.series_sp);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }

    private void setupSeriesListSp() {
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesAdapter);
    }

    private void setupApiMethods() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void fetchSeriesList() {
        Call<List<Series>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> seriesList = response.body();
                customSeriesAdapter.addAll(seriesList);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                showToast("Failed loaded data");
            }
        });
    }

    protected void showData() {
        movieIdTxt.setText(movie.moviesId);
        movieNameTxt.setText(movie.title);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesAdapter.getCount(); i++) {
            Series series = customSeriesAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                seriesSp.setSelection(i);
            }
        }
    }
}