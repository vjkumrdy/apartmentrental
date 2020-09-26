package com.findinganapartment.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.adapters.HomeAdapter;
import com.findinganapartment.models.HomePojo;

import java.util.ArrayList;
import java.util.List;

public class PropertyScreenActivity extends AppCompatActivity {
    RecyclerView property_recyclerView;
    List<HomePojo> a1;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_screen);

        getSupportActionBar().setTitle("  Properties");


        property_recyclerView = (RecyclerView)findViewById(R.id.property_recyclerView);
        a1 = new ArrayList<>();
        a1.add(new HomePojo("https://thearchitectsdiary.com/wp-content/uploads/2020/02/pexels-photo-106399-e1563903680874.jpeg","$1395-$3215","Apartment","1weekago","1-2 BED","1-2 BATH","530-942ft","PETS","One Month Free !"));
        a1.add(new HomePojo("https://thearchitectsdiary.com/wp-content/uploads/2020/02/pexels-photo-106399-e1563903680874.jpeg","$1395-$3215","Apartment","1weekago","1-2 BED","1-2 BATH","530-942ft","PETS","One Month Free !"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PropertyScreenActivity.this, LinearLayoutManager.VERTICAL,false);
        property_recyclerView.setLayoutManager(linearLayoutManager);

        homeAdapter=new HomeAdapter(PropertyScreenActivity.this,a1);  //attach adapter class with therecyclerview
        property_recyclerView.setAdapter(homeAdapter);
    }
}
