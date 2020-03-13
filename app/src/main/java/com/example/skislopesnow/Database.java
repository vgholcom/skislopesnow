package com.example.skislopesnow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Database extends AppCompatActivity {

    private Button create_database,insert_data, update_data, view_data;

    private MySqliteOpenHelper mySqliteOpenHelper;

    private SQLiteDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_database);

        create_database = (Button) findViewById(R.id.create_database);

        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // create the database with table here
                mySqliteOpenHelper = new MySqliteOpenHelper(getApplicationContext());

                mDatabase = mySqliteOpenHelper.getReadableDatabase();

                Cursor cursor = mDatabase.rawQuery("select * from resort ;", null);

                System.out.println("MainActivity.onClick:"+ cursor.getColumnCount());

            }


        });

        insert_data = (Button) findViewById(R.id.insert_data);

        insert_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        update_data = (Button) findViewById(R.id.update_data);
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });


    }



    public void openActivity3 () {
        String table_name = "resort";
        Intent intent = new Intent(this, InsertRow.class);
        startActivity(intent);
    }
    public void openActivity4 () {
        String table_name = "resort";
        Intent intent = new Intent(this, UpdateRow.class);
        startActivity(intent);
    }

}
