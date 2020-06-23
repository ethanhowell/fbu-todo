package com.example.simpletodo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
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
    }
}