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

public class HealthArticleActivity extends AppCompatActivity {

    private  String[][] healthArticle = {
            {"Walking Daily","","","","Click for......."},
            {"Covid-19","","","","Click for......."},
            {"Stop Smoking","","","","Click for......."},
            {"Beauty tips","","","","Click for......."},
            {"Healthy Food","","","","Click for......."},
    };

    private int[] images = {
            R.drawable.walking,
            R.drawable.covid,
            R.drawable.smoke,
            R.drawable.beauty,
            R.drawable.healthyfood,
    };

    HashMap<String,String> item;
    ArrayList arrayList;
    SimpleAdapter sa;

    Button btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);
        btnBack =findViewById(R.id.buttonArticle);
        listView =findViewById(R.id.listViewUserDetails);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleActivity.this, DashboardActivity.class));
            }
        });

        arrayList = new ArrayList();
        for(int i=0;i<healthArticle.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",healthArticle[i][0]);
            item.put("line2",healthArticle[i][1]);
            item.put("line3",healthArticle[i][2]);
            item.put("line4",healthArticle[i][3]);
            item.put("line5",healthArticle[i][4]);
            arrayList.add(item);
        }
        sa = new SimpleAdapter(this,arrayList,
                R.layout.mulit_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent i1 = new Intent(HealthArticleActivity.this,HealthArticleDetailsActivity.class);
                i1.putExtra("text1",healthArticle[i][0]);
                i1.putExtra("text2",images[i]);
                startActivity(i1);
            }
        });
    }
}