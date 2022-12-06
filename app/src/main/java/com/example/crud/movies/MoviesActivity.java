package com.example.crud.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    public ArrayList<Movie> movies = new ArrayList<>();
    public RecyclerView moviesRv;
    public MoviesAdapter moviesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        initViews();
        getSupportActionBar().setTitle("Movies");
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMovies();
    }

    public void initViews() {
        moviesRv = findViewById(R.id.movies_rv);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisible() {
        progressBar.setVisibility(View.GONE);
    }

    public void fetchMovies() {
        showVisible();
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hideVisible();
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
                Toast.makeText(MoviesActivity.this, "Successfully loaded data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                hideVisible();
                Toast.makeText(MoviesActivity.this, "Failed load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupMoviesRv() {
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movies);
        /*moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {

            }

            @Override
            public void onEdit(Movie movie) {

            }
        });*/
        moviesRv.setAdapter(moviesAdapter);
    }
}