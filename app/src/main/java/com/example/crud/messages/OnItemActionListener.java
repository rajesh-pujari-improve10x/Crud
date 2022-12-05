package com.example.crud.messages;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Message message);
}
