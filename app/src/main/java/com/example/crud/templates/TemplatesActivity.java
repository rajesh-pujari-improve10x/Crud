package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {

    private ArrayList<Template> templates = new ArrayList<>();
    private RecyclerView templatesRv;
    private TemplatesAdapter templatesAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        initViews();
        setupTemplatesAdapter();
        setupTemplatesRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        templatesRv = findViewById(R.id.templates_rv);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void setupTemplatesRv() {
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesRv.setAdapter(templatesAdapter);
    }

    private void setupTemplatesAdapter() {
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMessage(id);
            }

            @Override
            public void onEdit(Template template) {
                editMessage(template);
            }
        });
    }

    private void fetchData() {
        showProgressBar();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideProgressBar();
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load data");
            }
        });
    }

    private void deleteMessage(String id) {
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully Deleted Template");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed Delete Template");
            }
        });
    }

    private void editMessage(Template template) {
        Intent intent = new Intent(this, EditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, template);
        startActivity(intent);
    }
}