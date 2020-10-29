package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.findinganapartment.R;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.ResponseData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMyPropertiesActivity extends AppCompatActivity {
    EditText et_property_name,et_property_area,et_property_price,et_property_desc;
    Spinner spin_property_type,spin_property_beds,spin_bath_rooms,spin_pets,spin_location;
    String[] propertytype,propertybeds,bathroom,ppets,location;
    ImageView image_view;
    Button btn_submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_properties);

        getSupportActionBar().setTitle("Edit Property");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        propertytype=getResources().getStringArray(R.array.property_type);
        propertybeds=getResources().getStringArray(R.array.property_beds);
        bathroom=getResources().getStringArray(R.array.property_bathrooms);
        ppets=getResources().getStringArray(R.array.property_pets);
        location=getResources().getStringArray(R.array.location);

        et_property_name=(EditText)findViewById(R.id.et_property_name);
        et_property_area=(EditText)findViewById(R.id.et_property_area);
        et_property_price=(EditText)findViewById(R.id.et_property_price);
        et_property_desc=(EditText)findViewById(R.id.et_property_desc);

        spin_property_type=(Spinner)findViewById(R.id.spin_property_type);
        spin_property_beds=(Spinner)findViewById(R.id.spin_property_beds);
        spin_bath_rooms=(Spinner)findViewById(R.id.spin_bath_rooms);
        spin_pets=(Spinner)findViewById(R.id.spin_pets);
        spin_location=(Spinner)findViewById(R.id.spin_location);

        image_view=(ImageView)findViewById(R.id.image_view);
        Glide.with(this).load(getIntent().getStringExtra("image")).into(image_view);

        et_property_name.setText(getIntent().getStringExtra("property_name"));
        et_property_price.setText(getIntent().getStringExtra("price"));
        et_property_area.setText(getIntent().getStringExtra("area"));
        et_property_desc.setText(getIntent().getStringExtra("description"));

        //Toast.makeText(this, ""+getIntent().getStringExtra("pets"), Toast.LENGTH_SHORT).show();
        int ptype = new ArrayList<String>(Arrays.asList(propertytype)).indexOf(getIntent().getStringExtra("property_type"));
        spin_property_type.setSelection(ptype);

        int pbeds = new ArrayList<String>(Arrays.asList(propertybeds)).indexOf(getIntent().getStringExtra("beds"));
        spin_property_beds.setSelection(pbeds);

        int bath = new ArrayList<String>(Arrays.asList(bathroom)).indexOf(getIntent().getStringExtra("bath"));
        spin_bath_rooms.setSelection(bath);

        int pets = new ArrayList<String>(Arrays.asList(ppets)).indexOf(getIntent().getStringExtra("pets"));
        spin_pets.setSelection(pets);

        int loca = new ArrayList<String>(Arrays.asList(location)).indexOf(getIntent().getStringExtra("location"));
        spin_location.setSelection(loca);

         btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProerty();
            }
        });


    }
    ProgressDialog pd;
    public  void updateProerty() {
        pd = new ProgressDialog(EditMyPropertiesActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.updateproperty(getIntent().getStringExtra("id"),et_property_name.getText().toString(),spin_property_type.getSelectedItem().toString()
        ,spin_property_beds.getSelectedItem().toString(),spin_bath_rooms.getSelectedItem().toString(),et_property_area.getText().toString()
        ,spin_pets.getSelectedItem().toString(),et_property_price.getText().toString(),et_property_desc.getText().toString(),spin_location.getSelectedItem().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(EditMyPropertiesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditMyPropertiesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(EditMyPropertiesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
