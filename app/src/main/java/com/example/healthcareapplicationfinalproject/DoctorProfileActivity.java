package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DoctorProfileActivity extends AppCompatActivity {

    EditText editTextCategory, editTextDoctorType, editTextLocation, editTextExp, editTextPhone, editTextFees;
    Button buttonForRequest ,btnBack;

    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);


        editTextDoctorType = findViewById(R.id.editTextDoctorType);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextExp = findViewById(R.id.editTextExp);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextFees = findViewById(R.id.editTextFees);
        buttonForRequest = findViewById(R.id.ButtonForRequest);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnBack = findViewById(R.id.ButtonForBack);

        Database db = new Database(this);


        // Populate the Spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.doctor_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // Handle Spinner item selection
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection
            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorProfileActivity.this,DashboardActivity.class));
            }
        });

        // Set onClickListener for the button
        buttonForRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = editTextDoctorType.getText().toString();
                String location = editTextLocation.getText().toString();
                String experience = editTextExp.getText().toString();
                String phone = editTextPhone.getText().toString();
                String feesStr = editTextFees.getText().toString();
                String doctorType = spinnerCategory.getSelectedItem().toString();
                Toast.makeText(DoctorProfileActivity.this, doctorType, Toast.LENGTH_SHORT).show();

                if (doctorType.isEmpty() || doctorName.isEmpty() || location.isEmpty() || experience.isEmpty() || phone.isEmpty() || feesStr.isEmpty()) {
                    Toast.makeText(DoctorProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    db.addDoctor(doctorType, doctorName, location, experience, phone, feesStr);
                    Toast.makeText(DoctorProfileActivity.this, "Doctor details added", Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            }
        });
    }

    private void clearFields() {
        editTextCategory.setText("");
        editTextDoctorType.setText("");
        editTextLocation.setText("");
        editTextExp.setText("");
        editTextPhone.setText("");
        editTextFees.setText("");
    }
    }
