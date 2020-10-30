package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.adapters.SearchPropertiesAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.PropertyPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilterActivity extends AppCompatActivity {
        RecyclerView property_recyclerView;
        List<PropertyPojo> a1;
        SearchPropertiesAdapter searchPropertiesAdapter;
        ProgressDialog progressDialog;
        SharedPreferences sharedPreferences;
        String session;
        EditText searchView;
        EditText et_search;
        Spinner spin_price,spin_ptype,spin_beds,spin_location;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_fragment);

            getSupportActionBar().setTitle("Property Details");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
            session = sharedPreferences.getString("uname", "def-val");





            property_recyclerView = (RecyclerView)findViewById(R.id.property_recyclerView);
            a1 = new ArrayList<>();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchFilterActivity.this, LinearLayoutManager.VERTICAL,false);
            property_recyclerView.setLayoutManager(linearLayoutManager);
            serverData();



        }

        public void serverData(){
            progressDialog = new ProgressDialog(SearchFilterActivity.this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();

            ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
            Call<List<PropertyPojo>> call = service.search(getIntent().getStringExtra("price"),getIntent().getStringExtra("location"),getIntent().getStringExtra("pbeds"),getIntent().getStringExtra("ptype"));

            call.enqueue(new Callback<List<PropertyPojo>>() {
                @Override
                public void onResponse(Call<List<PropertyPojo>> call, Response<List<PropertyPojo>> response) {
                    progressDialog.dismiss();
                    if(response.body()==null){
                        Toast.makeText(SearchFilterActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                    }

                    if(response.body().size() == 0){
                        Toast.makeText(SearchFilterActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        a1 = response.body();
                        searchPropertiesAdapter=new SearchPropertiesAdapter(SearchFilterActivity.this,a1);  //attach adapter class with therecyclerview
                        property_recyclerView.setAdapter(searchPropertiesAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<PropertyPojo>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(SearchFilterActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }