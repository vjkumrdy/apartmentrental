package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.findinganapartment.R;
import com.findinganapartment.adapters.SearchPropertiesAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;

import com.findinganapartment.models.PricePojo;
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
    Spinner spin_price,spin_ptype;
    String[] propertytype;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_properties);

        getSupportActionBar().setTitle("Search Properties");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar)findViewById(R.id.rangeSeekbar1);
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                Toast.makeText(SearchPropertiesActivity.this, ""+maxValue, Toast.LENGTH_SHORT).show();
                //Toast.makeText(SearchPropertiesActivity.this, ""+minValue, Toast.LENGTH_SHORT).show();
                //searchPropertiesAdapter.price_filter(""+maxValue);

            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });



       spin_price=(Spinner)findViewById(R.id.spin_price);
        spin_ptype=(Spinner)findViewById(R.id.spin_ptype);
        spin_ptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0){

                   searchPropertiesAdapter.property_type_filter(spin_ptype.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        apartmentprice();






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

   private void apartmentprice() {
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
       Call<List<PricePojo>> call = apiService.getprices();
        call.enqueue(new Callback<List<PricePojo>>() {
            @Override
            public void onResponse(Call<List<PricePojo>> call, Response<List<PricePojo>> response) {
                if (response.isSuccessful()) {
                    final List<PricePojo> p_price=response.body();
                    if(p_price!=null) {
                        if(p_price.size()>0) {

                            ArrayList<String> price = new ArrayList<String>();
                            price.add("Select Price");
                            for (int i = 0; i < p_price.size(); i++) {
                                price.add(p_price.get(i).getP_price());
                            }
                            ArrayAdapter<String> adp = new ArrayAdapter<String>(SearchPropertiesActivity.this, android.R.layout.simple_spinner_dropdown_item, price);
                            spin_price.setAdapter(adp);
                            spin_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(i > 0){
                                        searchPropertiesAdapter.price_filter(spin_price.getSelectedItem().toString());
                                    }


                                  /*  String apartmentprice = spin_price.getSelectedItem().toString().toLowerCase(Locale.getDefault());
                                    if(spin_price.getSelectedItem().equals("Select Price")){
                                        serverData();
                                    }
                                    else {


                                    }*/


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    //serverData();

                                }
                            });


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PricePojo>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
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
