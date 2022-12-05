package com.example.crud.templates;

import com.example.crud.templates.Template;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Template template);
}
