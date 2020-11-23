package com.example.coursewebapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coursewebapptest.Database.DBHelper;

import java.util.List;

public class UserList extends AppCompatActivity {

    ListView lvUsersUL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        lvUsersUL = findViewById(R.id.lvUsersUL);


        DBHelper dbHelper = new DBHelper(getApplicationContext());

        List UserList = dbHelper.readAll();

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, UserList);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        lvUsersUL.setAdapter(adapter);




    }
}