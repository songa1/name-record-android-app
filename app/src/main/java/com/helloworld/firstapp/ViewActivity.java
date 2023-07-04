package com.helloworld.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ListView viewNames;
    private Button back;
    private LauncherActivity.ListItem item;
    private DatabaseHelper databaseHelper;
    public String phoneNumber;
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

            viewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    phoneNumber = parent.getItemAtPosition(position).toString().split("<")[1];
                    intent.setData(Uri.parse("tel:*182*1*1*"+phoneNumber+"#"));
                    startActivity(intent);
                }
            });

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dataList);

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