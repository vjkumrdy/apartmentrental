package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.adapters.ViewPropertyImageAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.GetPhotosPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPropertyImagesActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ViewPropertyImageAdapter viewPropertyImageAdapter;
    RecyclerView property_recyclerView;
    List<GetPhotosPojo> a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_images);


        getSupportActionBar().setTitle("Images");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        property_recyclerView = (RecyclerView) findViewById(R.id.property_recyclerView);
        a1 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewPropertyImagesActivity.this, LinearLayoutManager.VERTICAL, false);
        property_recyclerView.setLayoutManager(linearLayoutManager);
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(ViewPropertyImagesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetPhotosPojo>> call = service.getphotos(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<GetPhotosPojo>>() {
            @Override
            public void onResponse(Call<List<GetPhotosPojo>> call, Response<List<GetPhotosPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(ViewPropertyImagesActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    viewPropertyImageAdapter=new ViewPropertyImageAdapter(ViewPropertyImagesActivity.this,a1);  //attach adapter class with therecyclerview
                    property_recyclerView.setAdapter(viewPropertyImageAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetPhotosPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewPropertyImagesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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