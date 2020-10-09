package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.adapters.LandLordDashboardAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.PropertyPojo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandLordDashboardActivity extends AppCompatActivity {
    RecyclerView property_recyclerView;
    List<PropertyPojo> a1;
    LandLordDashboardAdapter landLordDashboardAdapter;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DrawerLayout dl;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_lord_dashboard);

        getSupportActionBar().setTitle("Land Lord Dashboard");
        navigationView();

        property_recyclerView = (RecyclerView)findViewById(R.id.property_recyclerView);
        a1 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LandLordDashboardActivity.this, LinearLayoutManager.VERTICAL,false);
        property_recyclerView.setLayoutManager(linearLayoutManager);
        serverData();


    }
    private void navigationView(){
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                        Intent home=new Intent(getApplicationContext(), AddPropertyActivity.class);
                        startActivity(home);
                        break;

                    case R.id.myprofile:
                        Intent myprofile=new Intent(getApplicationContext(), LandLordEditProfileActivity.class);
                        startActivity(myprofile);
                        break;

                    case R.id.add_property:
                        Intent add_property=new Intent(getApplicationContext(), AddPropertyActivity.class);
                        startActivity(add_property);
                        break;

                    case R.id.My_properties:
                        Intent my_property=new Intent(getApplicationContext(), AddPropertyActivity.class);
                        startActivity(my_property);
                        break;

                    case R.id.logout:
                        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        return true;
                }
                dl.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void serverData(){
        progressDialog = new ProgressDialog(LandLordDashboardActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<PropertyPojo>> call = service.getproperties();
        call.enqueue(new Callback<List<PropertyPojo>>() {
            @Override
            public void onResponse(Call<List<PropertyPojo>> call, Response<List<PropertyPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(LandLordDashboardActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    landLordDashboardAdapter =new LandLordDashboardAdapter(LandLordDashboardActivity.this,a1);  //attach adapter class with therecyclerview
                    property_recyclerView.setAdapter(landLordDashboardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<PropertyPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LandLordDashboardActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
