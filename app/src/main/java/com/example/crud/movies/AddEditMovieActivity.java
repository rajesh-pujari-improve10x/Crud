package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends AppCompatActivity {

    public EditText movieIdTxt;
    public EditText movieNameTxt;
    public Spinner seriesSp;
    public EditText imageUrlTxt;
    public EditText descriptionTxt;
    public CustomSeriesAdapter customSeriesAdapter;
    public ArrayList<Series> seriesList = new ArrayList<>();
    public CrudApi crudApi;
    public CrudService crudService;
    public Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        initViews();
        setupSeriesListSp();
        fetchSeriesList();
        if (getIntent().hasExtra("Movie")) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) getIntent().getSerializableExtra("Movie");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Movie");
        }
    }

    public void initViews() {
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesSp = findViewById(R.id.series_sp);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String movieId = movieIdTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            Series series = (Series) seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = imageUrlTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            if (movie == null) {
                addMovie(movieId, movieName, seriesId, imageUrl, description);
            } else {
                updateMovie(movie.id, movieId, movieName, seriesId, imageUrl, description);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void setupSeriesListSp() {
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesAdapter);
    }

    public void apiClasses() {
        crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    public void showData() {
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

    public void fetchSeriesList() {
        apiClasses();
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

            }
        });
    }

    /*public void stringObj() {
         String movieId = movieIdTxt.getText().toString();
         String movieName = movieNameTxt.getText().toString();
         Series series = (Series) seriesSp.getSelectedItem();
         String seriesId = series.seriesId;
         String imageUrl = imageUrlTxt.getText().toString();
         String description = descriptionTxt.getText().toString();
    }*/

    public void addMovie(String movieId, String movieName, String seriesId, String imageUrl, String description) {
        this.movie = new Movie(movieId, movieName, seriesId, imageUrl, description);

        apiClasses();
        Call<Movie> call = crudService.createMovie(this.movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to Add Movie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateMovie(String id, String movieId, String movieName, String seriesId, String imageUrl, String description) {
        movie = new Movie(movieId, movieName, seriesId, imageUrl, description);

        apiClasses();
        Call<Void> call = crudService.updateMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to update Movie", Toast.LENGTH_SHORT).show();
            }
        });
    }
}