package com.helloworld.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ListView viewNames;
    private Button back;
    private DatabaseHelper databaseHelper;
//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        try {
            viewNames = findViewById(R.id.listView);
            databaseHelper = new DatabaseHelper(getApplicationContext());
            back = findViewById(R.id.backHome);

            ArrayList<String> dataList = databaseHelper.fetchNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);

            viewNames.setAdapter(adapter);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });
        } catch (Exception e) {

            display("Error", e.getMessage());
            System.out.println(e.getMessage());

        }
    }

    public void display (String type, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(type);
        builder.setMessage(message);
        builder.show();
    }
}