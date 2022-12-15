package com.example.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView mobileNumberTxt;
    TextView messageTextTxt;
    ImageButton deleteBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        mobileNumberTxt = itemView.findViewById(R.id.mobile_number_txt);
        messageTextTxt = itemView.findViewById(R.id.message_text_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
