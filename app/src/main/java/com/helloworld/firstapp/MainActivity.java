package com.helloworld.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button addName;
    private Button getNames;
    private EditText name;
    private EditText phone;
    private Button articles;
    private DatabaseHelper databaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            databaseHelper = new DatabaseHelper(getApplicationContext());

            name = findViewById(R.id.nameField);
            phone = findViewById(R.id.phoneField);
            addName = findViewById(R.id.insertName);
            getNames = findViewById(R.id.viewNames);
            articles = findViewById(R.id.readArticles);

            addName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String getName = name.getText().toString();
                    String getPhone = phone.getText().toString();
                    databaseHelper.insertName(new Data(getName, getPhone));
                    name.setText("");
                    phone.setText("");
                }
            });

            getNames.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                    startActivity(i);
                }
            });

            articles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ArticlesActivity.class);
                    startActivity(i);
                }
            });
        } catch (Exception e) {
            display("Error", e.getMessage());
//            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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