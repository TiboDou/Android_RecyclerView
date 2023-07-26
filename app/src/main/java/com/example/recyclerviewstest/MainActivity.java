package com.example.recyclerviewstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    private ArrayList<String> herissonTitle = new ArrayList<>();
    private ArrayList<String> herissonDetails = new ArrayList<>();
    private ArrayList<Integer> imageList = new ArrayList<>();
    String phrase1 = "Mon animal favori";
    String phrase2 = "Mon deuxième animal favori";
    String phrase3 = "Mon troisième animal favoriiiiiiiiiiiiiiiiiiiiiii";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        for (int i = 0; i < 3; i++) {
            herissonTitle.add(phrase1);
            herissonTitle.add(phrase2);
            herissonTitle.add(phrase3);

            herissonDetails.add("Une description précise");
            herissonDetails.add("Une description précise");
            herissonDetails.add("Une description précise");

            imageList.add(R.drawable.herisson1);
            imageList.add(R.drawable.herisson2);
            imageList.add(R.drawable.herisson3);
        }

        adapter = new RecyclerAdapter(herissonTitle, herissonDetails, imageList, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }
}