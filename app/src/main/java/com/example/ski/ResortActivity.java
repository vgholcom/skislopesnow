package com.example.ski;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ski.database.SqliteDatabase;

import java.util.ArrayList;

public class ResortActivity extends AppCompatActivity {

    private SqliteDatabase databaseHelper;
    private ArrayList<Resorts> allResorts=new ArrayList<>();
    private ImageView deleteResort, editResort;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort);

        databaseHelper = SqliteDatabase.getInstance(this);
        allResorts = databaseHelper.listContacts();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final int position = intent.getIntExtra("message_key", 0);
        final Resorts resorts = allResorts.get(position);

        // Capture the layout's TextView and set the string as its text
        TextView resortTextView = findViewById(R.id.resortName);
        resortTextView.setText(resorts.getResort());

        TextView locTextView = findViewById(R.id.resortLocation);
        locTextView.setText(resorts.getLoc());

        TextView statusTextView = findViewById(R.id.resortStatus);
        statusTextView.setText(resorts.getStatus());

        TextView hoursTextView = findViewById(R.id.resortHours);
        hoursTextView.setText(resorts.getHours());

        TextView halfTextView = findViewById(R.id.resortHalfday);
        halfTextView.setText(resorts.getHalfday());

        TextView fullTextView = findViewById(R.id.resortFullDay);
        fullTextView.setText(resorts.getFullday());

        TextView weatherTextView = findViewById(R.id.resortWeather);
        weatherTextView.setText(resorts.getWeather());

        TextView snowTextView = findViewById(R.id.resortSnow);
        snowTextView.setText(resorts.getSnow());


        deleteResort = findViewById(R.id.delete_contact);
        deleteResort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                databaseHelper.deleteContact(resorts.getId());

                // go back to main page
                Intent intent = new Intent(ResortActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        editResort = findViewById(R.id.edit_contact);
        editResort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDatabase(position);
            }
        });
    }

    private void editDatabase(int position){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("message_key", position);
        startActivity(intent);
    }
}
