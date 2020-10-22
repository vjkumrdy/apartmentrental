package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.adapters.AllPropertiesAdapter;
import com.findinganapartment.adapters.SearchPropertiesAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.PropertyPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPropertiesActivity extends AppCompatActivity {
    EditText et_search;
    ProgressDialog progressDialog;
    RecyclerView property_recyclerView;
    SearchPropertiesAdapter searchPropertiesAdapter;
    List<PropertyPojo> a1;
    EditText searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_properties);

        getSupportActionBar().setTitle("Search Properties");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        property_recyclerView=(RecyclerView)findViewById(R.id.property_recyclerView);
        a1 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchPropertiesActivity.this, LinearLayoutManager.VERTICAL,false);
        property_recyclerView.setLayoutManager(linearLayoutManager);
        serverData();
        textchanger();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(SearchPropertiesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<PropertyPojo>> call = service.userviewpropertylist();
        call.enqueue(new Callback<List<PropertyPojo>>() {
            @Override
            public void onResponse(Call<List<PropertyPojo>> call, Response<List<PropertyPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(SearchPropertiesActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    searchPropertiesAdapter=new SearchPropertiesAdapter(SearchPropertiesActivity.this,a1);  //attach adapter class with therecyclerview
                    property_recyclerView.setAdapter(searchPropertiesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<PropertyPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SearchPropertiesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void textchanger(){

        et_search = (EditText)findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = et_search.getText().toString().toLowerCase(Locale.getDefault());
                searchPropertiesAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
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
