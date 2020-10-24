package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.findinganapartment.R;
import com.findinganapartment.Utils;

import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.MyRequestPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantRequestActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<MyRequestPojo> a1;
    SharedPreferences sharedPreferences;
    //TenantRequestAdapter myRequestAdapter;
    String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_request);
        getSupportActionBar().setTitle("My Requests");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("uname", "def-val");

        list_view=(ListView)findViewById(R.id.list_view);
        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(TenantRequestActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyRequestPojo>> call = service.tenantrequests(session);
        call.enqueue(new Callback<List<MyRequestPojo>>() {
            @Override
            public void onResponse(Call<List<MyRequestPojo>> call, Response<List<MyRequestPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(TenantRequestActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    //myRequestAdapter=new TenantRequestAdapter(a1, TenantRequestActivity.this);  //attach adapter class with therecyclerview
                    //list_view.setAdapter(myRequestAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MyRequestPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TenantRequestActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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