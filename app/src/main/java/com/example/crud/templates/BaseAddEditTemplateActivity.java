package com.example.crud.templates;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected EditText messageTextTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_add_edit_template);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_template_menu, menu);
        return true;
    }

    private void initViews() {
        messageTextTxt = findViewById(R.id.message_text_txt);
    }
}