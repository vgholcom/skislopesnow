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
        int position = intent.getIntExtra("message_key", 0);
        final Contacts contacts = allContacts.get(position);

        // Capture the layout's TextView and set the string as its text
        TextView resortTextView = findViewById(R.id.resortName);
        resortTextView.setText(contacts.getResort());

        TextView locTextView = findViewById(R.id.resortLocation);
        locTextView.setText(contacts.getLoc());

        TextView statusTextView = findViewById(R.id.resortStatus);
        statusTextView.setText(contacts.getStatus());

        TextView hoursTextView = findViewById(R.id.resortHours);
        hoursTextView.setText(contacts.getHours());

        TextView halfTextView = findViewById(R.id.resortHalfday);
        halfTextView.setText(contacts.getHalfday());

        TextView fullTextView = findViewById(R.id.resortFullDay);
        fullTextView.setText(contacts.getFullday());

        TextView weatherTextView = findViewById(R.id.resortWeather);
        weatherTextView.setText(contacts.getWeather());

        TextView snowTextView = findViewById(R.id.resortSnow);
        snowTextView.setText(contacts.getSnow());
    }
}
