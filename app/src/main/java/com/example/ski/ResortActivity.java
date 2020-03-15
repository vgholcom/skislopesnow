package com.example.ski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ski.database.SqliteDatabase;

import java.util.ArrayList;

public class ResortActivity extends AppCompatActivity {

    private SqliteDatabase databaseHelper;
    private ArrayList<Contacts> allContacts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort);

        databaseHelper = SqliteDatabase.getInstance(this);
        allContacts = databaseHelper.listContacts();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.message_key);
        int position = intent.getIntExtra("message_key", 0);
        final Contacts contacts = allContacts.get(position);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView3);
        textView.setText(contacts.getResort());
    }
}
