package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView todoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.mainRV);
        todoTV = findViewById(R.id.todoTV);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<TodoCard> content = new ArrayList<TodoCard>();

        Gson gson = new Gson();
        InputStreamReader state = SaveSystem.ReadState(getApplicationContext());
        if (state != null) {
            Log.e("asd", "onCreate: _________ "+state);
            DataItems items = gson.fromJson(state, DataItems.class);
            content.addAll(items.getTodoCards());
            for (TodoCard card : items.getTodoCards())
                Log.e("asd", "onCreate: _________ "+card.Checked);
        }
        mAdapter = new MyAdapter(content, getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }

    public void addCard(View v) {
        ((MyAdapter) mAdapter).add(new TodoCard(false, todoTV.getText().toString()));
    }
}
