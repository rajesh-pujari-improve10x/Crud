package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardListActivity extends AppCompatActivity {

    public ArrayList<Dashboard> dashBoards;
    public RecyclerView dashBoardRv;
    public Dashboard dashBoard;
    public DashBoardsAdapter dashBoardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashBoardRv();
    }

    public void setupData() {
        dashBoards = new ArrayList<>();

        this.dashBoard = new Dashboard("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Messages");
        dashBoards.add(this.dashBoard);

        this.dashBoard = new Dashboard("https://static.thenounproject.com/png/1021190-200.png", "Templates");
        dashBoards.add(this.dashBoard);

        this.dashBoard = new Dashboard("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Series");
        dashBoards.add(this.dashBoard);

        this.dashBoard = new Dashboard("https://images.frandroid.com/wp-content/uploads/2019/07/android-messages.png", "Movies");
        dashBoards.add(this.dashBoard);
    }

    public void setupDashBoardRv() {
        dashBoardRv = findViewById(R.id.dash_board_rv);
        dashBoardRv.setLayoutManager(new LinearLayoutManager(this));
        dashBoardsAdapter = new DashBoardsAdapter();
        dashBoardsAdapter.setData(dashBoards);
        dashBoardRv.setAdapter(dashBoardsAdapter);
    }
}