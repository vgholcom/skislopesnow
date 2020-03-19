package com.example.ski;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ski.database.SqliteDatabase;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private Context context;
    private SqliteDatabase databaseHelper;
    Button submitButton, backButton;
    private ArrayList<Resorts> allResorts=new ArrayList<>();

    //Intent intent = getIntent();
    //final int position = intent.getIntExtra("message_key", 0);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        databaseHelper = SqliteDatabase.getInstance(this);
        allResorts = databaseHelper.listContacts();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final int position = intent.getIntExtra("message_key", 0);
        final Resorts resorts = allResorts.get(position);

        setContentView(R.layout.add_contact_layout);

        final EditText resortField = (EditText)findViewById(R.id.resortInput);
        final EditText locField = (EditText)findViewById(R.id.locInput);
        final EditText statusField = (EditText)findViewById(R.id.statusInput);
        final EditText hoursField = (EditText)findViewById(R.id.hoursInput);
        final EditText fulldayField = (EditText)findViewById(R.id.fulldayInput);
        final EditText halfdayField = (EditText)findViewById(R.id.halfdayInput);
        final EditText weatherField = (EditText)findViewById(R.id.weatherInput);
        final EditText snowField = (EditText)findViewById(R.id.snowInput);

        resortField.setText(resorts.getResort());
        locField.setText(resorts.getLoc());
        statusField.setText(resorts.getStatus());
        hoursField.setText(resorts.getHours());
        halfdayField.setText(resorts.getHalfday());
        fulldayField.setText(resorts.getFullday());
        weatherField.setText(resorts.getWeather());
        snowField.setText(resorts.getSnow());

        // Submit and go back to main page
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String resort = resortField.getText().toString();
                final String loc = locField.getText().toString();
                final String status = statusField.getText().toString();
                final String hours = hoursField.getText().toString();
                final String fullday = fulldayField.getText().toString();
                final String halfday = halfdayField.getText().toString();
                final String weather= weatherField.getText().toString();
                final String snow = snowField.getText().toString();


                databaseHelper.updateResorts(new Resorts(resorts.getId(), resort, loc, status, hours, fullday, halfday, weather, snow));

                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Cancel and go back to main page
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}