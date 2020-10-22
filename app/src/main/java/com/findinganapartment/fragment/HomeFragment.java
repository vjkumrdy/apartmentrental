package com.findinganapartment.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.adapters.HomeAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.PropertyPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView property_recyclerView;
    List<PropertyPojo> a1;
    HomeAdapter homeAdapter;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
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

        sharedPreferences = getActivity().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("uname", "def-val");


        property_recyclerView = (RecyclerView)view.findViewById(R.id.property_recyclerView);
        a1 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        property_recyclerView.setLayoutManager(linearLayoutManager);
        serverData();




        return view;
    }

    public void serverData(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<PropertyPojo>> call = service.userviewpropertylist();
        call.enqueue(new Callback<List<PropertyPojo>>() {
            @Override
            public void onResponse(Call<List<PropertyPojo>> call, Response<List<PropertyPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(getContext(),"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    homeAdapter=new HomeAdapter(getActivity(),a1,session);  //attach adapter class with therecyclerview
                    property_recyclerView.setAdapter(homeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<PropertyPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}