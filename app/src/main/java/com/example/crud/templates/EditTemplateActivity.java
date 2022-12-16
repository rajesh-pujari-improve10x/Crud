package com.example.crud.templates;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddEditTemplateActivity{

    private Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Template");
        if(getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            template = (Template) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String message = messageTextTxt.getText().toString();
            updateTemplate(template.id, message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        messageTextTxt.setText(template.messageText);
    }

    private void updateTemplate(String id, String message) {
        Template updatedTemplate = new Template(message);
        Call<Void> call = crudService.updateTemplate(id, updatedTemplate);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully Updated Template");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to Update Template");
            }
        });
    }
}
