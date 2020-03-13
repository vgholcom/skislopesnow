package com.example.skislopesnow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    private static final String database_name = "resort.db";
    private String table_name = "resort";
    private static final int database_version = 1;

    public static String column_snow = "snow", column_weather = "weather", column_resort = "resort", column_status = "status";
    public static String column_hours = "hours", column_fullday = "fullday", column_halfday = "halfday";


    private String database_create_statement =
            "create table "  + table_name + "(" +column_resort+ " text," +column_status+ " text," +column_hours+ " text," +column_snow+ " text," +column_weather+ " text," +column_halfday+ " text," +column_fullday+ " text);";

    public MySqliteOpenHelper(Context context)
    {
        super(context, database_name, null, database_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(database_create_statement);

    }

    public void insertRow(String resort, String status, String hours, String snow, String weather, String halfday, String fullday, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_resort, resort);
        contentValues.put(column_status, status);
        contentValues.put(column_hours, hours);
        contentValues.put(column_snow, snow);
        contentValues.put(column_weather, weather);
        contentValues.put(column_halfday, halfday);
        contentValues.put(column_fullday, fullday);
        db.insert(table_name, null, contentValues);

    }

    public Cursor readResort(SQLiteDatabase db)
    {
        String[] projections = {column_resort, column_status, column_hours, column_snow, column_weather, column_halfday, column_fullday};
        Cursor cursor = db.query(table_name, projections, null, null, null, null, null);
        return cursor;
    }

    public void updateRow(String resort, String status, String hours, String snow, String weather, String halfday, String fullday, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_status, status);
        contentValues.put(column_hours, hours);
        contentValues.put(column_snow, snow);
        contentValues.put(column_weather, weather);
        contentValues.put(column_halfday, halfday);
        contentValues.put(column_fullday, fullday);

        String selection = column_resort + " LIKE ?";
        String[] selection_args = {resort};

        db.update(table_name, contentValues, selection, selection_args);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
       //changes in the database
    }


}
