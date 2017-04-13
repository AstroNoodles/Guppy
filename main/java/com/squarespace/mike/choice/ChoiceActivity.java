package com.squarespace.mike.choice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        RecyclerView rv = (RecyclerView) findViewById(R.id.main);
        rv.setAdapter(new ChoiceRecycler(this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }




}
