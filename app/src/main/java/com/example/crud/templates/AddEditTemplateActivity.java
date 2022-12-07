package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        initView();
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

    private void addMessage(String message) {
        Template template = new Template();
        template.messageText = message;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully Added Template", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed Add Template", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        messageTxt.setText(template.messageText);
    }

    private void updateMessage(String id, String message) {
        Template template = new Template();
        template.messageText = message;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.updateTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully Updated Template", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed to Update Template", Toast.LENGTH_SHORT).show();
            }
        });
    }
}