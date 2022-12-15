package com.example.crud.dashboard;
    //Todo : change the class name in all places DashboardItem
public class DashboardItem {

    public String imageUrl;
    public String title;

    public DashboardItem() {
    }
    //Todo : change the parameters order
    public DashboardItem(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }
}
