package com.oz_heng.apps.android.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oz_heng.apps.android.habittracker.habit.HabitContract.HabitEntry;
import com.oz_heng.apps.android.habittracker.habit.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String SEP = "; ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper habitDbHelper = new HabitDbHelper(this);

        // Write 3 habits into the database.
        habitDbHelper.insert("Team lunch", "21-Dec-17", 1,
                "With Scott, Matt, Sean and Chloe at Taylor's Rooftop Bar.");
        habitDbHelper.insert("Coffee", "22-Dec-17", 2,
                "At Bar Bellacino.");
        habitDbHelper.insert("Gym", "23-Dec-17", 1,
                "It was hot.");

        // Read the habits from the database and write them in the logcat.
        Cursor cursor = habitDbHelper.read();

        if (cursor != null) {
            int whatCI = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_WHAT);
            int whenCI = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_WHEN);
            int nbrOfOccurrencesCI = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_OCCURRENCES);
            int notesCI = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NOTES);

            while (cursor.moveToNext()) {
                String what = cursor.getString(whatCI);
                String when = cursor.getString(whenCI);
                int nbrOfOccurrences = cursor.getInt(nbrOfOccurrencesCI);
                String notes = cursor.getString(notesCI);

                Log.v(LOG_TAG, what + SEP + when + SEP + nbrOfOccurrences + SEP +
                notes);
            }

            cursor.close();
        }
    }
}
