package com.example.simpletodo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;
    Button btnAdd;
    EditText etTodo;
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        etTodo = findViewById(R.id.etAdd);
        rvList = findViewById(R.id.rvList);

        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Buy cheese");
        items.add("Drink milk");

        final ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        rvList.setAdapter(itemsAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(etTodo.getText().toString());
                itemsAdapter.notifyItemChanged(items.size() - 1);
                etTodo.setText("");
                Toast.makeText(getApplicationContext(), "Item successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}