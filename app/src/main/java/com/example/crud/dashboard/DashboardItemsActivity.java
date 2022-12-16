package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ArrayList<DashboardItem> dashboardItems;
    private RecyclerView dashboardItemsRv;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        initViews();
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

    private void initViews() {
        dashboardItemsRv = findViewById(R.id.dashboard_items_rv);
    }

    private void setupDashboardItemsRv() {
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }
}