package com.example.crud.dashboard;
    //Todo : change the class name in all places DashboardItem
public class Dashboard {

    public String imageUrl;
    public String title;

    public Dashboard() {
    }
    //Todo : change the parameters order
    public Dashboard(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }
}
