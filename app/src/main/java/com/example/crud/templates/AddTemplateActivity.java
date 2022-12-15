package com.example.crud.templates;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplateActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String message = messageTxt.getText().toString();
            addMessage(message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    //Todo: change the method names to addTemplate, deleteTemplate, UpdateTemplate, createTemplate
    private void addMessage(String message) {
        Template template = new Template();
        template.messageText = message;
        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("Successfully Added Template");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                showToast("Failed Add Template");
            }
        });
    }
}
