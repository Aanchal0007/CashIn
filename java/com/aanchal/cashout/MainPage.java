package com.aanchal.cashout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainPage extends AppCompatActivity {
    TextView btnmoviesobj, btnbusticketsobj, btnvouchersobj;
    ImageButton btnbusactivityobj;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnmoviesobj = findViewById(R.id.btnmovies);
        btnbusticketsobj = findViewById(R.id.btnbustickets);
        btnvouchersobj = findViewById(R.id.btnvouchers);
        btnbusactivityobj=findViewById(R.id.btnbusactivity);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.btnhome);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.btnhome:
                        return true;
                    case R.id.btnpost:
                        startActivity(new Intent(getApplicationContext(), PostActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.btnprofile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        btnbusticketsobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this, BusSearchActivity.class);
                startActivity(i);
            }
        });
        btnbusactivityobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPage.this, BusSearchActivity.class);
                startActivity(i);
            }
        });
        btnmoviesobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this, MovieSearchActivity.class);
                startActivity(i);
            }
        });
        btnvouchersobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this, VoucherActivity.class);
                startActivity(i);
            }
        });

    }
}