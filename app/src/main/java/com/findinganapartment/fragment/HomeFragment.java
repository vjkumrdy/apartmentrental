package com.findinganapartment.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.adapters.HomeAdapter;
import com.findinganapartment.models.HomePojo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView property_recyclerView;
    List<HomePojo> a1;
    HomeAdapter homeAdapter;
    View view;

    public static HomeFragment homeFragment() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");


        property_recyclerView = (RecyclerView)view.findViewById(R.id.property_recyclerView);
        a1 = new ArrayList<>();
        a1.add(new HomePojo("https://thearchitectsdiary.com/wp-content/uploads/2020/02/pexels-photo-106399-e1563903680874.jpeg","$1395-$3215","Apartment","1weekago","1-2 BED","1-2 BATH","530-942ft","PETS","One Month Free !"));
        a1.add(new HomePojo("https://thearchitectsdiary.com/wp-content/uploads/2020/02/pexels-photo-106399-e1563903680874.jpeg","$1395-$3215","Apartment","1weekago","1-2 BED","1-2 BATH","530-942ft","PETS","One Month Free !"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        property_recyclerView.setLayoutManager(linearLayoutManager);

        homeAdapter=new HomeAdapter(getActivity(),a1);  //attach adapter class with therecyclerview
        property_recyclerView.setAdapter(homeAdapter);


        return view;
    }
}