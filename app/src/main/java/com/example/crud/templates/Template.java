package com.example.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Template implements Serializable {

    @SerializedName("_id")
    public String id;
    public String messageText;

    //Todo: Create a constructor in Template java class after use the constructor in the addMessage and updateMessage Method
}
