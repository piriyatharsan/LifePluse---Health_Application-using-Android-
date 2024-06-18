package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
    private HashMap<String, String> item;
    private ArrayList<HashMap<String, String>> list;
    private SimpleAdapter sa;
    private TextView tvTotal;
    private ListView listView;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckOut, btnBack;
    private Database database;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        // Initialize views
        dateButton = findViewById(R.id.buttonCartDatePicker);
        timeButton = findViewById(R.id.ButtonCartTimePicker);
        btnCheckOut = findViewById(R.id.buttonCartCheckOut);
        btnBack = findViewById(R.id.buttonCartBack);
        tvTotal = findViewById(R.id.textViewCartTotalPrice);
        listView = findViewById(R.id.listViewCart);

        // Initialize database
        database = new Database(this);

        // Retrieve username from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("share_Prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Fetch data from the database
        float totalAmount = 0;
        ArrayList<String> dbData = database.getCartData(username, "Lab");
        Toast.makeText(getApplicationContext(), "" + dbData, Toast.LENGTH_SHORT).show();
        Log.i("LabTest", "" + dbData);
        Log.i("Count", "" + dbData.size());

        packages = new String[dbData.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }

        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i);
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost :" + strData[1] + "/=";
            try {
                totalAmount = totalAmount + Float.parseFloat(strData[1]);
            } catch (NumberFormatException e) {
                Log.e("CartLabActivity", "Error parsing price: " + strData[1], e);
            }
        }
        tvTotal.setText("Total Cost :" +totalAmount);

        // Prepare the list for the adapter
        list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.mulit_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(sa);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CartLabActivity.this,LabTestBookActivity.class);
                i1.putExtra("price",tvTotal.getText());
                i1.putExtra("date",dateButton.getText());
                i1.putExtra("time",timeButton.getText());
                startActivity(i1);
            }
        });

        // Set up button listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this, LabTestActivity.class));
            }
        });


        // DatePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // TimePicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateButton.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay + ":" + (minute < 10 ? "0" + minute : minute));
            }
        };
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hours, mins, true);
    }
}
