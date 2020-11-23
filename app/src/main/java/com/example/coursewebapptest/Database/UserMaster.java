package com.example.coursewebapptest.Database;

import android.provider.BaseColumns;

public final class UserMaster {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserMaster() {}

    /* Inner class that defines the table contents */
    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "UserData";
        public static final String COLUMN_NAME_USERNAME = "UserName";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final String COLUMN_NAME_TYPE = "Type";
        public static final String COLUMN_NAME_VEHICLE = "Vehicle";
    }
}

