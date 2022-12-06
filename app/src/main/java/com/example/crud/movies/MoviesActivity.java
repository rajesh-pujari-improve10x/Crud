package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditMovieActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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
        moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovie(id);
            }

            @Override
            public void onEdit(Movie movie) {
                editMovie(movie);
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    public void deleteMovie(String id) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, "Failed delete Movie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editMovie(Movie movie) {
        Intent intent = new Intent(this, AddEditMovieActivity.class);
        intent.putExtra("Movie", movie);
        startActivity(intent);
    }
}