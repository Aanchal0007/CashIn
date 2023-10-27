package com.aanchal.cashout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PostActivity extends AppCompatActivity {
    SQLiteDatabase Database;
    private static String namevalue, FromCompanyvalue, tovalue, timeLinkvalue, costvalue;

    public static String getNamevalue() {
        return namevalue;
    }

    public static String getCostvalue() {
        return costvalue;
    }

    public static String getFromCompanyvalue() {
        return FromCompanyvalue;
    }

    public static String getTovalue() {
        return tovalue;
    }

    public static String getTimeLinkvalue() {
        return timeLinkvalue;
    }


    CheckBox btnbustktobj, btnmovietktobj, btnvoucherobj;
    Button btncreatepostobj, buspostdoneobj, moviepostdoneobj, voucherpostdoneobj;
    RelativeLayout buspostlayoutobj, moviepostlayoutobj, voucherpostlayoutobj;
    EditText edtbusname, edtbusfrom, edtbusto, edtbustime, edtbuscost, edtmoviename, edtmovietime, edtmoviecost, edtvouchername, edtvouchercompany, edtvouchercost, edtvoucherlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btncreatepostobj = findViewById(R.id.btncreatepost);
        buspostlayoutobj = findViewById(R.id.buspostlayout);
        moviepostlayoutobj = findViewById(R.id.moviepostlayout);
        voucherpostlayoutobj = findViewById(R.id.voucherpostlayout);
        btnbustktobj = findViewById(R.id.btnbustkt);
        btnmovietktobj = findViewById(R.id.btnmovietkt);
        btnvoucherobj = findViewById(R.id.btnvoucher);
        buspostdoneobj = findViewById(R.id.buspostdone);
        moviepostdoneobj = findViewById(R.id.moviepostdone);
        voucherpostdoneobj = findViewById(R.id.voucherpostdone);
        edtbusname = findViewById(R.id.edtbusname);
        edtbusfrom = findViewById(R.id.edtbusfrom);
        edtbusto = findViewById(R.id.edtbusto);
        edtbustime = findViewById(R.id.edtbustime);
        edtmoviename = findViewById(R.id.edtmoviename);
        edtmovietime = findViewById(R.id.edtmovietime);
        edtmoviecost = findViewById(R.id.edtmoviecost);
        edtvouchername = findViewById(R.id.edtvouchername);
        edtvouchercompany = findViewById(R.id.edtvouchercomapny);
        edtvouchercost = findViewById(R.id.edtvouchercost);
        edtvoucherlink = findViewById(R.id.edtvoucherlink);
        edtbuscost = findViewById(R.id.edtbuscost);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.btnpost);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.btnhome:
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.btnpost:
                        return true;
                    case R.id.btnprofile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        Database = openOrCreateDatabase("PostDataDB", Context.MODE_PRIVATE, null);
        Database.execSQL("CREATE TABLE IF NOT EXISTS Buspostdata(BusName VARCHAR PRIMARY KEY,BusFrom VARCHAR,BusTo VARCHAR,BusTime NUMBER,BusCost NUMBER);");
        Database.execSQL("CREATE TABLE IF NOT EXISTS MoviePostData(MovieName VARCHAR PRIMARY KEY,MovieTime NUMBER,MovieCost NUMBER);");
        Database.execSQL("CREATE TABLE IF NOT EXISTS VoucherData(VoucherName VARCHAR PRIMARY KEY,VoucherCompany VARCHAR,VoucherCost NUMBER,VoucherLink VARCHAR);");

        btncreatepostobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnbustktobj.isChecked() == true) {
                    buspostlayoutobj.setVisibility(View.VISIBLE);
                    moviepostlayoutobj.setVisibility(View.GONE);
                    voucherpostlayoutobj.setVisibility(View.GONE);
                } else if (btnmovietktobj.isChecked() == true) {
                    moviepostlayoutobj.setVisibility(View.VISIBLE);
                    buspostlayoutobj.setVisibility(View.GONE);
                    voucherpostlayoutobj.setVisibility(View.GONE);
                } else if (btnvoucherobj.isChecked() == true) {
                    voucherpostlayoutobj.setVisibility(View.VISIBLE);
                    buspostlayoutobj.setVisibility(View.GONE);
                    moviepostlayoutobj.setVisibility(View.GONE);
                }

            }
        });

        buspostdoneobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtbusname == null || edtbusfrom == null || edtbusto == null || edtbustime == null || edtbuscost == null) {
                    Toast.makeText(PostActivity.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (v.getId() == R.id.buspostdone) {
                        ContentValues values = new ContentValues();

                        values.put("BusName", String.valueOf(edtbusname));
                        values.put("BusFrom", String.valueOf(edtbusfrom));
                        values.put("BusTo", String.valueOf(edtbusto));
                        values.put("BusTime", String.valueOf(edtbusto));
                        values.put("BusCost", String.valueOf(edtbuscost));

                        long newRowId = Database.insert("Buspostdata", null, values);
                        Toast.makeText(PostActivity.this, "Stored", Toast.LENGTH_SHORT).show();
                        namevalue = edtbusname.getText().toString().trim();
                        FromCompanyvalue = edtbusfrom.getText().toString().trim();
                        tovalue = edtbusto.getText().toString().trim();
                        timeLinkvalue = edtbustime.getText().toString().trim();
                        costvalue = edtbuscost.getText().toString().trim();
                        Intent i = new Intent(PostActivity.this, BillActivity.class);
                        startActivity(i);
                        edtbusname.setText("");
                        edtbusfrom.setText("");
                        edtbusto.setText("");
                        edtbustime.setText("");
                        edtbuscost.setText("");
                    }
                }
            }
        });

        moviepostdoneobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtmoviename == null || edtmovietime == null || edtmoviecost == null) {
                    Toast.makeText(PostActivity.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (v.getId() == R.id.moviepostdone) {
                        ContentValues values = new ContentValues();

                        values.put("MovieName", String.valueOf(edtmoviename));
                        values.put("MovieTime", String.valueOf(edtmovietime));
                        values.put("MovieCost", String.valueOf(edtmoviecost));

                        long newRowId = Database.insert("MoviePostData", null, values);
                        Toast.makeText(PostActivity.this, "Stored", Toast.LENGTH_SHORT).show();
                        namevalue = edtmoviename.getText().toString().trim();
                        timeLinkvalue = edtmovietime.getText().toString().trim();
                        costvalue = edtmoviecost.getText().toString().trim();
                        Intent i = new Intent(PostActivity.this, BillActivity.class);
                        startActivity(i);
                        edtmoviename.setText("");
                        edtmovietime.setText("");
                        edtmoviecost.setText("");
                    }
                }
            }
        });
        voucherpostdoneobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtvouchername == null || edtvouchercompany == null || edtvouchercost == null || edtvoucherlink == null) {
                    Toast.makeText(PostActivity.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (v.getId() == R.id.voucherpostdone) {
                        ContentValues values = new ContentValues();

                        values.put("VoucherName", String.valueOf(edtvouchername));
                        values.put("VoucherCompany", String.valueOf(edtvouchercompany));
                        values.put("VoucherCost", String.valueOf(edtvouchercost));
                        values.put("VoucherLink", String.valueOf(edtvoucherlink));

                        long newRowId = Database.insert("MoviePostData", null, values);
                        Toast.makeText(PostActivity.this, "Stored", Toast.LENGTH_SHORT).show();
                        namevalue = edtvouchername.getText().toString().trim();
                        FromCompanyvalue = edtvouchercompany.getText().toString().trim();
                        timeLinkvalue = edtvoucherlink.getText().toString().trim();
                        costvalue = edtvouchercost.getText().toString().trim();
                        Intent i = new Intent(PostActivity.this, BillActivity.class);
                        startActivity(i);
                        edtvouchername.setText("");
                        edtvouchercompany.setText("");
                        edtvouchercost.setText("");
                        edtvoucherlink.setText("");
                    }
                }
            }
        });

    }
}