package com.example.ski.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.ski.Contacts;


public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "resort";
    private	static final String TABLE_CONTACTS = "resorts";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_RESORT = "resort";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_HOURS = "hours";
    private static final String COLUMN_FULLDAY = "fullday";
    private static final String COLUMN_HALFDAY = "halfday";
    private static final String COLUMN_WEATHER = "weather";
    private static final String COLUMN_SNOW = "snow";

    private static SqliteDatabase sInstance;

    public static synchronized SqliteDatabase getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new SqliteDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_CONTACTS_TABLE = "CREATE	TABLE " + TABLE_CONTACTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_RESORT + " TEXT," +
                COLUMN_STATUS + " TEXT," + COLUMN_HOURS + " TEXT," + COLUMN_FULLDAY + " TEXT," + COLUMN_HALFDAY + " TEXT," +
                COLUMN_WEATHER + " TEXT," + COLUMN_SNOW + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public ArrayList<Contacts> listContacts(){
        String sql = "select * from " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Contacts> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String resort = cursor.getString(1);
                String status = cursor.getString(2);
                String hours = cursor.getString(3);
                String fullday = cursor.getString(4);
                String halfday = cursor.getString(5);
                String weather = cursor.getString(6);
                String snow = cursor.getString(7);

                storeContacts.add(new Contacts(id, resort, status, hours, fullday, halfday, weather, snow));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }

    public void addContacts(Contacts contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESORT, contacts.getResort());
        values.put(COLUMN_STATUS, contacts.getStatus());
        values.put(COLUMN_HOURS, contacts.getHours());
        values.put(COLUMN_FULLDAY, contacts.getFullday());
        values.put(COLUMN_HALFDAY, contacts.getHalfday());
        values.put(COLUMN_WEATHER, contacts.getWeather());
        values.put(COLUMN_SNOW, contacts.getSnow());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, values);
    }

    public void updateContacts(Contacts contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESORT, contacts.getResort());
        values.put(COLUMN_STATUS, contacts.getStatus());
        values.put(COLUMN_HOURS, contacts.getHours());
        values.put(COLUMN_FULLDAY, contacts.getFullday());
        values.put(COLUMN_HALFDAY, contacts.getHalfday());
        values.put(COLUMN_WEATHER, contacts.getWeather());
        values.put(COLUMN_SNOW, contacts.getSnow());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_CONTACTS, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(contacts.getId())});
    }

    public Contacts findContacts(String name){
        String query = "Select * FROM "	+ TABLE_CONTACTS + " WHERE " + COLUMN_RESORT + " = " + "name";
        SQLiteDatabase db = this.getWritableDatabase();
        Contacts contacts = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String contactsResort = cursor.getString(1);
            String contactsStatus = cursor.getString(2);
            String contactsHours = cursor.getString(3);
            String contactsFullday = cursor.getString(4);
            String contactsHalfday = cursor.getString(5);
            String contactsWeather = cursor.getString(6);
            String contactsSnow = cursor.getString(7);

            contacts = new Contacts(id, contactsResort, contactsStatus, contactsHours, contactsFullday, contactsHalfday, contactsWeather, contactsSnow);
        }
        cursor.close();
        return contacts;
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
