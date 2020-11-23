package com.example.coursewebapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coursewebapptest.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText etUserNameLogin, etPasswordLogin;
    Spinner spinnerTypeLogin;
    CheckBox cbCarLogin, cbVanLogin;
    Button btnInsertLogin, btnUserListLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserNameLogin = findViewById(R.id.etUserNameLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);

        spinnerTypeLogin = findViewById(R.id.spinnerTypeLogin);

        cbCarLogin = findViewById(R.id.cbCarLogin);
        cbVanLogin = findViewById(R.id.cbVanLogin);

        btnInsertLogin = findViewById(R.id.btnInsertLogin);
        btnUserListLogin = findViewById(R.id.btnUserListLogin);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerTypeLogin.setAdapter(adapter);



        btnInsertLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Vehicle = "Null";

                if (cbCarLogin.isChecked()){
                    Vehicle = "Car";
                }else if(cbVanLogin.isChecked()){
                    Vehicle = "Van";
                }else if (cbCarLogin.isChecked() && cbVanLogin.isChecked()){
                    Vehicle = "Van & Car";
                }
                else{
                    Vehicle = "Null";
                }

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                boolean statusInsert = dbHelper.addUser(etUserNameLogin.getText().toString(),etPasswordLogin.getText().toString(),spinnerTypeLogin.getSelectedItem().toString(),Vehicle);
                
                if (statusInsert){
                    Toast.makeText(LoginActivity.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Insert Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}