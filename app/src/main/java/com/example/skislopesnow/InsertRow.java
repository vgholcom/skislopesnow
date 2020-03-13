package com.example.skislopesnow;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertRow extends AppCompatActivity {

    String resort, status, hours, weather;
    String snow, halfday, fullday;

    Context context = this;
    MySqliteOpenHelper mySqliteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    EditText resortInput;
    EditText statusInput;
    EditText hoursInput;
    EditText weatherInput;
    EditText snowInput;
    EditText halfdayInput;
    EditText fulldayInput;

    Button submitButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);

        resortInput = (EditText) findViewById(R.id.resortInput);
        statusInput = (EditText) findViewById(R.id.statusInput);
        hoursInput = (EditText) findViewById(R.id.hoursInput);
        weatherInput = (EditText) findViewById(R.id.weatherInput);
        snowInput = (EditText) findViewById(R.id.snowInput);
        halfdayInput = (EditText) findViewById(R.id.halfdayInput);
        fulldayInput = (EditText) findViewById(R.id.fulldayInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resort = resortInput.getText().toString();
                status = statusInput.getText().toString();
                hours = hoursInput.getText().toString();
                weather = weatherInput.getText().toString();
                snow = snowInput.getText().toString();
                halfday = halfdayInput.getText().toString();
                fullday = fulldayInput.getText().toString();

                showToast(resort);
                showToast(status);
                showToast(hours);
                showToast(snow);
                showToast(weather);
                showToast(halfday);
                showToast(fullday);

                mySqliteOpenHelper = new MySqliteOpenHelper(context);
                sqLiteDatabase = mySqliteOpenHelper.getWritableDatabase();
                mySqliteOpenHelper.insertRow(resort, status, hours, snow, weather, halfday, fullday, sqLiteDatabase);


                Toast.makeText(getBaseContext(), "Row Inserted",Toast.LENGTH_LONG).show();
                mySqliteOpenHelper.close();

            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(InsertRow.this, text, Toast.LENGTH_SHORT).show();
    }
    public void goBack(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
