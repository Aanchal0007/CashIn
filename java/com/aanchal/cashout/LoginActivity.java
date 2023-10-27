package com.aanchal.cashout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtusernameobj, edtpassobj;
    Button btnloginobj, btnregisterobj;
    DBHelper Db;
    public static String accname;

    public static String getAccname() {
        return accname;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnloginobj = findViewById(R.id.btnlogin);
        btnregisterobj = findViewById(R.id.btnregister);
        edtusernameobj = findViewById(R.id.edtusername);
        edtpassobj = findViewById(R.id.edtpass);
        Db = new DBHelper(this);


        btnloginobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtusernameobj.getText().toString();
                String pass = edtpassobj.getText().toString();

                if (edtusernameobj==null || edtpassobj==null)
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = Db.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        accname = edtusernameobj.getText().toString().trim();
                        Intent i = new Intent(LoginActivity.this, MainPage.class);
                        startActivity(i);
                    } else
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnregisterobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


    }
}