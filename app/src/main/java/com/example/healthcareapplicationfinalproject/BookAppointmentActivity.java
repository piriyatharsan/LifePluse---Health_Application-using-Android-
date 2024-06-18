package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    Button dateButton, timeButton,btnAppoiment,btnback;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewappAppoint);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppNumber);
        ed4= findViewById(R.id.editTextAppFee);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnAppoiment = findViewById(R.id.btnAppoinment);
        btnback = findViewById(R.id.BtnBack);
        database = new Database(this);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);


        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String number = it.getStringExtra("text4");
        String fees= it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(number);
        ed4.setText(fees+"/-");



        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // timePicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnAppoiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  type  = tv.getText().toString();
                String name = ed1.getText().toString();
                String address = ed2.getText().toString();
                String number = ed3.getText().toString();
                String fees = ed4.getText().toString();
                String date = dateButton.getText().toString();
                String time = timeButton.getText().toString();
                Log.d("Appointment", "Doctor Type: " + type);


                if (date.equals("") || time.equals("") || tv.equals("")){
                    Toast.makeText(getApplicationContext(), "Please select Date and Times", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = database.BookAppoinment(type,name,address,number,fees,date,time);
                    if (insert==true){
                        Toast.makeText(BookAppointmentActivity.this,"Book an Appointment",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookAppointmentActivity.this,DashboardActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Record not updated", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,DocterDetailsActivity.class));
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateButton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    };

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay+ ":"+minute);
            }

        };
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,timeSetListener,hours,mins,true);
    }


}