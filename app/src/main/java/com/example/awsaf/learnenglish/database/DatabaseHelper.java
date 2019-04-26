package com.example.awsaf.learnenglish.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.awsaf.learnenglish.database.model.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Task.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Task.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Task.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(Task.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Task getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Task.TABLE_NAME,
                new String[]{Task.COLUMN_ID, Task.COLUMN_NOTE, Task.COLUMN_TIMESTAMP},
                Task.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare task object
        Task task = new Task(
                cursor.getInt(cursor.getColumnIndex(Task.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Task.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Task.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return task;
    }

    public List<Task> getAllNotes() {
        List<Task> tasks = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Task.TABLE_NAME + " ORDER BY " +
                Task.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(Task.COLUMN_ID)));
                task.setNote(cursor.getString(cursor.getColumnIndex(Task.COLUMN_NOTE)));
                task.setTimestamp(cursor.getString(cursor.getColumnIndex(Task.COLUMN_TIMESTAMP)));

                tasks.add(task);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return tasks list
        return tasks;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Task.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Task.COLUMN_NOTE, task.getNote());

        // updating row
        return db.update(Task.TABLE_NAME, values, Task.COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    public void deleteNote(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Task.TABLE_NAME, Task.COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
        db.close();
    }
}
