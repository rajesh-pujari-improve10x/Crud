package com.example.crud.messages;

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

public class MessagesActivity extends AppCompatActivity {

    public ArrayList<Message> messages = new ArrayList<>();
    public RecyclerView messagesRv;
    public MessagesAdapter messagesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_activity);
        getSupportActionBar().setTitle("Messages");
        messageRv();
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideVisible() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditMessageActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchData() {
        showVisible();
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideVisible();
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideVisible();
                Toast.makeText(MessagesActivity.this, "Failed to load Messages", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void messageRv() {
        progressBar = findViewById(R.id.progress_bar);
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messages);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                Toast.makeText(MessagesActivity.this, "Successfully Deleted Message", Toast.LENGTH_SHORT).show();
                deleteMessage(id);
            }

            @Override
            public void onEdit(Message message) {
                Toast.makeText(MessagesActivity.this, "Successfully Edited Message", Toast.LENGTH_SHORT).show();
                editMessage(message);
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }


    public void deleteMessage(String id) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MessagesActivity.this, "Successfully deleted message", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Failed to delete message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editMessage(Message message) {
        Intent intent = new Intent(this, AddEditMessageActivity.class);
        intent.putExtra("Message", message);
        startActivity(intent);
    }
}