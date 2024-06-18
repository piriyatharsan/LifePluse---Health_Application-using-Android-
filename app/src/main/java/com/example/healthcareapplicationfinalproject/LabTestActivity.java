package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private  String[][] packages = {
            {"packeges 1 : Full body Test","","","","999"},
            {"packeges 2 : blood glucose Fasting","","","","299"},
            {"packeges 3 : Covid 19 antibody","","","","899"},
            {"packeges 4 : Thyroid checkup","","","","499"},
            {"packeges 5 : Immunity checkup","","","","699"}
    };

    private String[] packegeDetails = {
            "Blood Glucose Fasting\n"+
                    "HbA1c\n"+
                    "Iron Studies\n"+
                    "kidney function test\n"+
                    "LHD Lactate Dehydronenase, Serum\n"+
                    "lipid profile\n"+
                    "Liver function test",
            "Blood gulcose Fasting",
            "COVID-19 antibody - IgG",
            "Throid profile total(T3,T4 & TSH ultra-senstivie)",
            "Complate homogram\n"+
                    "CRP (C Reactive Protein) Qunatitative", "serum\n"+
            "Iron Studies\n"+
            "kidney function test\n"+
            "Vitamin D Total-25 hydroxy\n"+
            "Liver function test\n"+
            "lipid profile"
    };
    HashMap<String,String> item;
    ArrayList arrayList;
    SimpleAdapter sa;
    Button checkOut, goToBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        checkOut = findViewById(R.id.buttonCheckOut);
        goToBack = findViewById(R.id.buttonLabBack);
        listView = findViewById(R.id.listViewLabTest);


        goToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, DashboardActivity.class));
            }
        });


        arrayList = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4] +"/-");
            arrayList.add(item);
        }

        sa = new SimpleAdapter(this,arrayList,
                R.layout.mulit_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i1 = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                i1.putExtra("text1",packages[position][0]);
                i1.putExtra("text2",packegeDetails[position]);
                i1.putExtra("text3",packages[position][4]);
                startActivity(i1);
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });

    }
}