package com.aanchal.cashout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    ImageView backlogoobj;
    Button btnsignupobj, btnhaveaccobj;
    EditText edtnameobj, edtpassobj, edtrepassobj;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backlogoobj = findViewById(R.id.backlogo);
        btnsignupobj = findViewById(R.id.btnsignup);
        edtnameobj = findViewById(R.id.edtname);
        edtpassobj = findViewById(R.id.edtpass);
        btnhaveaccobj = findViewById(R.id.btnhaveacc);
        edtrepassobj = findViewById(R.id.edtrepass);
        DB = new DBHelper(this);

        backlogoobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        edtnameobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        edtpassobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnsignupobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtnameobj.getText().toString();
                String pass = edtpassobj.getText().toString();
                String repass = edtrepassobj.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(RegisterActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertdata(user, pass);
                            if (insert == true) {
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                            } else
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnhaveaccobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }
        });
    }

}