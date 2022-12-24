package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityMoviesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;
    private ActivityMoviesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Movies");
        setupMoviesAdapter();
        setupMoviesRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddMovieActivity.class);
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

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void setupMoviesRv() {
        binding.moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.moviesRv.setAdapter(moviesAdapter);
    }

    private void setupMoviesAdapter() {
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
    }

    private void fetchMovies() {
        showProgressBar();
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hideProgressBar();
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed load data");
            }
        });
    }

    private void deleteMovie(String id) {
        showProgressBar();
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideProgressBar();
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideProgressBar();
                showToast("Failed delete Movie");
            }
        });
    }

    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }
}