package com.aanchal.cashout;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.ExpandedMenuView;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class BusSearchActivity extends AppCompatActivity {
    private ListView boardlistView,droplistview;
    private ArrayList<String> boardinglist,droplist;
    Button btnbussearchojb;
    Button boardingsearch,droppingsearch,datesearch;
    private Calendar mycalender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);
        btnbussearchojb = findViewById(R.id.btnsearchbus);
        boardingsearch = findViewById(R.id.boardingSearchView);
        droppingsearch = findViewById(R.id.droppingSearchView);
        datesearch = findViewById(R.id.busdateSearchView);
        boardlistView = findViewById(R.id.boardlistview);
        droplistview=findViewById(R.id.droplistview);

        mycalender=Calendar.getInstance();

        boardingsearch.setTextColor(Color.BLACK);
        droppingsearch.setTextColor(Color.BLACK);
        datesearch.setTextColor(Color.BLACK);

        boardinglist=new ArrayList<>();
        boardinglist.add("Latur");
        boardinglist.add("Pune");
        boardinglist.add("Mumbai");
        boardinglist.add("Nagpur");
        boardinglist.add("Nashik");
        boardinglist.add("Ahmedpur");


        final ArrayAdapter<String> boardadapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,boardinglist);
        boardlistView.setAdapter(boardadapter);

        boardingsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardlistView.setVisibility(View.VISIBLE);
                boardlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String boardpointselected =(String) adapterView.getItemAtPosition(i);
                        boardingsearch.setText(boardpointselected);
                        boardlistView.setVisibility(View.GONE);
                    }
                });
            }
        });

        droplist=new ArrayList<>();
        droplist.add("Latur");
        droplist.add("Pune");
        droplist.add("Mumbai");
        droplist.add("Nagpur");
        droplist.add("Nashik");
        droplist.add("Ahmedpur");

        final ArrayAdapter<String> dropadapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,droplist);
        droplistview.setAdapter(dropadapter);
        droppingsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                droplistview.setVisibility(View.VISIBLE);
                droplistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String droppointselected=(String) adapterView.getItemAtPosition(i);
                        droppingsearch.setText(droppointselected);
                        droplistview.setVisibility(View.GONE);
                    }
                });
            }
        });


        datesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new DatePickerDialog(BusSearchActivity.this, dateSetListener,mycalender.get(Calendar.YEAR),mycalender.get(Calendar.MONTH),mycalender.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

//        btnbussearchojb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(BusSearchActivity.this, BusActivity.class);
//                startActivity(i);
//            }
//        });

        btnbussearchojb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BusSearchActivity.this, SharmaBusBuy.class);
                startActivity(i);
            }
        });
    }
    private DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mycalender.set(Calendar.DAY_OF_MONTH,day);
            mycalender.set(Calendar.MONTH,month);
            mycalender.set(Calendar.YEAR,year);
           updateLabel();

        }
    };
    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        System.out.print("Date Picked Is :");
//        System.out.println(sdf.format(mycalender.getTime());
        datesearch.setText(sdf.format(mycalender.getTime()));
    }
}