package com.aanchal.cashout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    SQLiteDatabase Database;
    Button btnbuysobj, btnsellsobj;
    ImageButton btnsettingsobj, profilepic;
    TextView txvaccnameobj, txvnullobj;
    int num_buy = 0, num_sell = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txvaccnameobj = findViewById(R.id.txvaccname);
        btnbuysobj = findViewById(R.id.btnbuys);
        btnsellsobj = findViewById(R.id.btnsells);
        txvnullobj = findViewById(R.id.txvnull);
        btnsettingsobj = findViewById(R.id.btnsettings);
        profilepic = findViewById(R.id.profilepic);


        Database = openOrCreateDatabase("login.db", Context.MODE_PRIVATE, null);


        txvaccnameobj.setText(LoginActivity.getAccname());

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Alert!! ");
                builder.setMessage("Set New Profile?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");

                    startActivity(photoPickerIntent);
                    onActivityResult(0, 1, photoPickerIntent);
                    System.out.println("Imside Select Image");


                });
                builder.setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.show();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.btnprofile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.btnhome:
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.btnpost:
                        startActivity(new Intent(getApplicationContext(), PostActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.btnprofile:
                        return true;
                }
                return false;
            }
        });
        btnbuysobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num_buy == 0)
                    txvnullobj.setVisibility(View.VISIBLE);

            }
        });
        btnsellsobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num_sell == 0)
                    txvnullobj.setVisibility(View.VISIBLE);
            }
        });
        btnsettingsobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profilepic.setImageBitmap(selectedImage);
                System.out.println(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(ProfileActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
//    }
    }
}