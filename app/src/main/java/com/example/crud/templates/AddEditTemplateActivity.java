package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.network.CrudApi;
import com.example.crud.network.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {

    private Template template;
    private EditText messageTxt;
    private CrudApi crudApi;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        Log.i("AddEditTemplateActivity", "onCreate Called");
        initView();
        setupApiMethods();
        if(getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Template");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String message = messageTxt.getText().toString();
            if (this.template == null) {
                addMessage(message);
            } else {
                updateMessage(template.id, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        messageTxt = findViewById(R.id.message_txt);
    }

    private void setupApiMethods() {
        crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupToast(String template) {
        Toast.makeText(this, template, Toast.LENGTH_SHORT).show();
    }

    private void addMessage(String message) {
        Template template = new Template();
        template.messageText = message;

        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                setupToast("Successfully Added Template");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                setupToast("Failed Add Template");
            }
        });
    }

    private void showData() {
        messageTxt.setText(template.messageText);
    }

    private void updateMessage(String id, String message) {
        Template template = new Template();
        template.messageText = message;

        Call<Void> call = crudService.updateTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setupToast("Successfully Updated Template");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setupToast("Failed to Update Template");
            }
        });
    }
}