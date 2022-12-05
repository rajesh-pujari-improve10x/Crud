package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<Template> templates = new ArrayList<>();
    public RecyclerView templatesRv;
    public TemplatesAdapter templatesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        initView();
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
            Intent intent = new Intent(this, AddEditTemplateActivity.class);
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

    public void initView() {
        progressBar = findViewById(R.id.progress_bar);
        templatesRv = findViewById(R.id.templates_rv);
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisible() {
        progressBar.setVisibility(View.GONE);
    }

    public void editMessage(Template template) {
        Intent intent = new Intent(this, AddEditTemplateActivity.class);
        intent.putExtra("template", template);
        startActivity(intent);
    }

    public void fetchData() {
        showVisible();
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideVisible();
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                hideVisible();
            }
        });
    }

    public void setupTemplatesRv() {
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesRv.setAdapter(templatesAdapter);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                Toast.makeText(TemplatesActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                deleteMessage(id);
            }

            @Override
            public void onEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "Template Selected", Toast.LENGTH_SHORT).show();
                editMessage(template);
            }
        });
    }

    public void deleteMessage(String id) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully Deleted Template", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed Delete Template", Toast.LENGTH_SHORT).show();
            }
        });
    }
}