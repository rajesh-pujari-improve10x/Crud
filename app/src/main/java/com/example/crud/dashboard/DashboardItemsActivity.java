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
    //Todo : change the variable name dashboardItem
    private DashboardItem dashboardItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRv();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        //Todo: implement Dashboard Class Name
        this.dashboardItem = new DashboardItem("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Messages");
        dashboardItems.add(this.dashboardItem);
        this.dashboardItem = new DashboardItem("https://static.thenounproject.com/png/1021190-200.png", "Templates");
        dashboardItems.add(this.dashboardItem);
        this.dashboardItem = new DashboardItem("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Series");
        dashboardItems.add(this.dashboardItem);
        this.dashboardItem = new DashboardItem("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Movies");
        dashboardItems.add(this.dashboardItem);
    }

    private void setupDashboardItemsRv() {
        //Todo : create new method initViews
        dashboardItemsRv = findViewById(R.id.dashboard_rv);
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }
}