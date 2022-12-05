package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    public ArrayList<Dashboard> dashboards;
    public RecyclerView dashboardRv;
    public Dashboard dashboard;
    public DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardRv();
    }

    public void setupData() {
        dashboards = new ArrayList<>();

        this.dashboard = new Dashboard("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Messages");
        dashboards.add(this.dashboard);

        this.dashboard = new Dashboard("https://static.thenounproject.com/png/1021190-200.png", "Templates");
        dashboards.add(this.dashboard);

        this.dashboard = new Dashboard("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Series");
        dashboards.add(this.dashboard);

        this.dashboard = new Dashboard("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Movies");
        dashboards.add(this.dashboard);
    }

    public void setupDashboardRv() {
        dashboardRv = findViewById(R.id.dashboard_rv);
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboards);
        dashboardRv.setAdapter(dashboardAdapter);
    }
}