package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityDashBoardItemsBinding;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ActivityDashBoardItemsBinding binding;
    private ArrayList<DashboardItem> dashboardItems;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRv();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();

        DashboardItem messages = new DashboardItem("Messages", "https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png");
        dashboardItems.add(messages);
        DashboardItem templates = new DashboardItem("Templates", "https://static.thenounproject.com/png/1021190-200.png");
        dashboardItems.add(templates);
        DashboardItem seriesItems = new DashboardItem("Series", "https://www.sleepspa.in/wp-content/uploads/2020/04/money-heist-netflix.jpeg");
        dashboardItems.add(seriesItems);
        DashboardItem movies = new DashboardItem("Movies", "https://collegerealitycheck.com/wp-content/uploads/film-studies-degree-374366191-1024x768.jpg");
        dashboardItems.add(movies);
    }

    private void setupDashboardItemsRv() {
        binding.dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }
}