package com.oz_heng.apps.android.habittracker.habit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.oz_heng.apps.android.habittracker.habit.HabitContract.HabitEntry;


/**
 * Database helper. Manages database creation and version management.
 * Includes insert() and read() methods.
 */
public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habits.db";

    /** Database version */
    private static final int DATABASE_VERSION = 1;

    /** String constants for SQL keywords */
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String TEXT = " TEXT";
    private static final String INTEGER = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String AUTOINCREMENT = " AUTOINCREMENT";
    private static final String NOT_NULL = " NOT NULL";
    private static final String DEFAULT = " DEFAULT ";
    private static final String COMMA_SEP = ", ";


    /** String constant for the SQL statement to create the habits table */
    private static final String SQL_CREATE_ENTRIES =
            CREATE_TABLE + HabitEntry.TABLE_NAME + " (" +
                    HabitEntry._ID + INTEGER + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                    HabitEntry.COLUMN_HABIT_WHAT + TEXT + NOT_NULL + COMMA_SEP +
                    HabitEntry.COLUMN_HABIT_WHEN + TEXT + NOT_NULL + COMMA_SEP +
                    HabitEntry.COLUMN_HABIT_OCCURRENCES + INTEGER + NOT_NULL + DEFAULT + "1" + COMMA_SEP +
                    HabitEntry.COLUMN_HABIT_NOTES + TEXT +
                    ");";


    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    /**
     * Insert a habit into the habits database.
     * @param whatHabit What this habit is about.
     * @param when The date in which this habit occurred.
     * @param howManyTimes How many times this habit occurred on that date.
     * @param notes Notes.
     */
    public void insert(String whatHabit, String when, int howManyTimes, String notes) {
        ContentValues cv = new ContentValues();
        cv.put(HabitEntry.COLUMN_HABIT_WHAT, whatHabit);
        cv.put(HabitEntry.COLUMN_HABIT_WHEN, when);
        cv.put(HabitEntry.COLUMN_HABIT_OCCURRENCES, howManyTimes);
        cv.put(HabitEntry.COLUMN_HABIT_NOTES, notes);

        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert(HabitEntry.TABLE_NAME, null, cv);
        if (result != -1) {
            Log.v(LOG_TAG, "Habit saved with Id " + result + ".");
        } else {
            Log.e(LOG_TAG, "Error with saving the habit.");
        }
    }

    /**
     * Read the items saved in the database table.
     * @return a {@link Cursor} object.
     */
    public Cursor read() {
        // Select all the table columns.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_WHAT,
                HabitEntry.COLUMN_HABIT_WHEN,
                HabitEntry.COLUMN_HABIT_OCCURRENCES,
                HabitEntry.COLUMN_HABIT_NOTES
        };

        SQLiteDatabase db = getReadableDatabase();

        return db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null );
    }
}
