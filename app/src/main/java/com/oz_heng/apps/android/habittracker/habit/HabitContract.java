package com.oz_heng.apps.android.habittracker.habit;

import android.provider.BaseColumns;

/**
 * Defines name of the database table and constants.
 */
public class HabitContract {

    /**
     * An empty constructor to prevent the instantiation of this class.
     */
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static class HabitEntry implements BaseColumns {

        /** Name of the database table for habits */
        public static final String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * What the habit is about.
         *
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_WHAT = "habit_what";

        /**
         * The date in which the habit occurs.
         *
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_WHEN = "habit_when";

        /**
         * The number of times the habit occurred on that date.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_HABIT_OCCURRENCES = "habit_occurrences";

        /**
         * Notes on the habit.
         *
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_NOTES = "habit_notes";
    }
}
