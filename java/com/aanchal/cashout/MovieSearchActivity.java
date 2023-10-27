package com.aanchal.cashout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MovieSearchActivity extends AppCompatActivity {
    Button movielocation,movienamesearch, moviedateselect, btnmoviesearchobj;
    private ListView movielocationlist,movienamelist;
    private ArrayList<String> locationlist,movienames;
    private Calendar mycalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        btnmoviesearchobj=findViewById(R.id.btnsearchmovie);
        movielocation=findViewById(R.id.movielocation);
        movienamesearch=findViewById(R.id.movienamesearch);
        moviedateselect=findViewById(R.id.moviedateselect);
        movielocationlist=findViewById(R.id.movielocationlist);
        movienamelist=findViewById(R.id.movienamelist);

        mycalender= Calendar.getInstance();
        locationlist=new ArrayList<>();
        locationlist.add("Latur");
        locationlist.add("Pune");
        locationlist.add("Mumbai");
        locationlist.add("Nagpur");
        locationlist.add("Nashik");
        locationlist.add("Ahmedpur");

        movienames=new ArrayList<>();
        movienames.add("Leo");
        movienames.add("Jawaan");
        movienames.add("The NUN");
        movienames.add("Tu Jhutti Main Makkar");
        movienames.add("Jab We Met");
        movienames.add("Yeh Jawaani Hai Deewani");



        final ArrayAdapter<String> locationadapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,locationlist);
        movielocationlist.setAdapter(locationadapter);

        movielocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movielocationlist.setVisibility(View.VISIBLE);
                movielocationlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String locationselected =(String) adapterView.getItemAtPosition(i);
                        movielocation.setText(locationselected);
                        movielocationlist.setVisibility(View.GONE);
                    }
                });
            }
        });

        final ArrayAdapter<String> movienameadapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,movienames);
        movienamelist.setAdapter(movienameadapter);
        movienamesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movienamelist.setVisibility(View.VISIBLE);
                movienamelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String movieselected=(String) adapterView.getItemAtPosition(i);
                        movienamesearch.setText(movieselected);
                        movienamelist.setVisibility(View.GONE);
                    }
                });
            }
        });

        moviedateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MovieSearchActivity.this, dateSetListener,mycalender.get(Calendar.YEAR),mycalender.get(Calendar.MONTH),mycalender.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        btnmoviesearchobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MovieSearchActivity.this, MoviesActivity.class);
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
        moviedateselect.setText(sdf.format(mycalender.getTime()));
    }
}