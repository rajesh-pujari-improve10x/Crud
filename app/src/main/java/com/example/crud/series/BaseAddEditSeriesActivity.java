package com.example.crud.series;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditSeriesActivity extends BaseActivity {

    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
    protected EditText seriesImageUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Todo: change the menu xml file name
        getMenuInflater().inflate(R.menu.add_edit_series_item, menu);
        return true;
    }

    private void initView() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImageUrlTxt = findViewById(R.id.series_image_url_txt);
    }
}