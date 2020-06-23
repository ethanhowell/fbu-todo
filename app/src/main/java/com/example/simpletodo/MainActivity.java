package com.example.simpletodo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String TAG = getClass().getCanonicalName();

    List<String> items;
    Button btnAdd;
    EditText etTodo;
    RecyclerView rvList;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        etTodo = findViewById(R.id.etAdd);
        rvList = findViewById(R.id.rvList);

        loadItems();

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                items.remove(position);
                writeItems();
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item successfully removed", Toast.LENGTH_SHORT).show();
            }
        };
        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rvList.setAdapter(itemsAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(etTodo.getText().toString());
                writeItems();
                itemsAdapter.notifyItemChanged(items.size() - 1);
                etTodo.setText("");
                Toast.makeText(getApplicationContext(), "Item successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }

    private void writeItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e(TAG, "writeItems: error writing to file", e);
        }
    }

    private void loadItems() {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e(TAG, "error loading items from file", e);
            items = new ArrayList<>();
        }
    }

}