package com.example.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.example.Activities.AchievmentsAdapter;
import com.example.Classes.Achievments;
import com.example.ecohelp.R;

import java.util.ArrayList;
import java.util.List;

public class AchievmetsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    List<Achievments> achievmentss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievmets);
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Достижения");
        }

        recyclerView = findViewById(R.id.listAchievments);

        AchievmentsAdapter adapter = new AchievmentsAdapter(this,achievmentss);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setInitialData1();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //или this.finish или что то свое
        return true;
    }
    public void  setInitialData1(){
        achievmentss.add(new Achievments("Велосипедист",R.drawable.bicycle1,"Полезный едок",R.drawable.food,"Хранитель леса",R.drawable.forest));
        achievmentss.add(new Achievments("Защитник атмосферы",R.drawable.factory,"Переработка опасных отходов",R.drawable.barrel,"Сберегательный",R.drawable.electricity));


    }
}
