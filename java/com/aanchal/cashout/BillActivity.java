package com.aanchal.cashout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BillActivity extends AppCompatActivity {
    SQLiteDatabase Database;
    TextView productname_atbillobj, productfrom_company_atbillobj, productTo_atbill, productTime_link_atbill, productCost_atbill;
    RadioGroup rdgpayusing;
    RadioButton btndebitcardobj, btnUPIobj;
    LinearLayout layoutdebitcaardobj, layoutUPIobj, layout_postpreviewobj;
    EditText edtcardnumberobj, edtcardexpiryobj, edtcvv, edtUPI_IDobj;
    Button btnUPI_verify, btnsellobj, btnbuycancelobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        layout_postpreviewobj = findViewById(R.id.layout_postpreview);
        btndebitcardobj = findViewById(R.id.btndebitcard);
        btnUPIobj = findViewById(R.id.btnUPI);
        layoutdebitcaardobj = findViewById(R.id.layoutdebitcard);
        layoutUPIobj = findViewById(R.id.layoutUPI);
        edtcardnumberobj = findViewById(R.id.edtcardnumber);
        edtcardexpiryobj = findViewById(R.id.edtcardexpiry);
        edtcvv = findViewById(R.id.edtcvv);
        edtUPI_IDobj = findViewById(R.id.edtUPI_ID);
        btnUPI_verify = findViewById(R.id.btnUPI_Verify);
        btnsellobj = findViewById(R.id.btnsell);
        btnbuycancelobj = findViewById(R.id.btnbuycancel);
        productname_atbillobj = findViewById(R.id.productname_atbill);
        productfrom_company_atbillobj = findViewById(R.id.productFrom_companyname_atbill);
        productTo_atbill = findViewById(R.id.productTo_atbill);
        productTime_link_atbill = findViewById(R.id.producttime_link_atbill);
        productCost_atbill = findViewById(R.id.productcost_atbill);
        rdgpayusing = findViewById(R.id.rdgpayusing);


        Database = openOrCreateDatabase("BillDatabase", Context.MODE_PRIVATE, null);
        Database.execSQL("CREATE TABLE IF NOT EXISTS CardData(DebitCardNumber NUMBER PRIMARY KEY,DebitCardExpiry VARCHAR,DebitCVV NUMBER);");
        Database.execSQL("CREATE TABLE IF NOT EXISTS UPIData(UPIID VARCHAR);");
        Database.execSQL("CREATE TABLE IF NOT EXISTS PostDetails(ProductName VARCHAR PRIMARY KEY,FromOrCompany VARCHAR, BusTo VARCHAR,TimeOrLink VARCHAR,ProductCost VARCHAR);");


        productname_atbillobj.setText(PostActivity.getNamevalue());
        productfrom_company_atbillobj.setText(PostActivity.getFromCompanyvalue());
        productfrom_company_atbillobj.setText(PostActivity.getFromCompanyvalue());
        productTo_atbill.setText(PostActivity.getTovalue());
        productTime_link_atbill.setText(PostActivity.getTimeLinkvalue());
        productTime_link_atbill.setText(PostActivity.getTimeLinkvalue());
        productCost_atbill.setText(PostActivity.getCostvalue());


        rdgpayusing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (btndebitcardobj.isChecked() == true) {
                    layoutdebitcaardobj.setVisibility(View.VISIBLE);
                    layoutUPIobj.setVisibility(View.GONE);
                } else if (btnUPIobj.isChecked()) {
                    layoutUPIobj.setVisibility(View.VISIBLE);
                    layoutdebitcaardobj.setVisibility(View.GONE);
                }
            }
        });

        btnsellobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnsell) {
                    if (btndebitcardobj.isChecked() == true) {
                        Database.execSQL("INSERT INTO CardData(DebitCardNumber,DebitCardExpiry,DebitCVV) VALUES ('" + edtcardnumberobj.getText() + "','" + edtcardexpiryobj.getText() + "'," + edtcvv.getText() + ")");
                        Toast.makeText(BillActivity.this, "Card Data Inserted", Toast.LENGTH_SHORT).show();
                        edtcardnumberobj.setText("");
                        edtcardexpiryobj.setText("");
                        edtcvv.setText("");
                    } else if (btnUPIobj.isChecked() == true) {
                        Database.execSQL("INSERT INTO UPIData(UPIID) VALUES (" + edtUPI_IDobj.getText() + "))");
                        Toast.makeText(BillActivity.this, "UPI Stored Data", Toast.LENGTH_SHORT).show();
                        edtUPI_IDobj.setText("");
                    }
                    else {
                        Toast.makeText(BillActivity.this, "Enter Bank Details", Toast.LENGTH_SHORT).show();
                    }

                    ContentValues values = new ContentValues();
                    values.put("ProductName", String.valueOf(productname_atbillobj));
                    values.put("FromOrCompany", String.valueOf(productfrom_company_atbillobj));
                    values.put("BusTo", String.valueOf(productTo_atbill));
                    values.put("TimeOrLink", String.valueOf(productfrom_company_atbillobj));
                    values.put("ProductCost", String.valueOf(productCost_atbill));

                    long newRowId = Database.insert("PostDetails", null, values);
                    Toast.makeText(BillActivity.this, "Post Stored", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}