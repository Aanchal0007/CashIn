package com.aanchal.cashout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SharmaBusBuy extends AppCompatActivity {
    Button btnbuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharma_bus_buy);
        btnbuy=findViewById(R.id.btnbuy);

        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(SharmaBusBuy.this, CartActivity.class);
                startActivity(i);
            }
        });
    }
}