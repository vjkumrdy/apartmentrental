package com.findinganapartment.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.findinganapartment.R;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReplyActivity extends AppCompatActivity {

    TextView property_id,property_name;
    Button btn_book_now;
    EditText et_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        getSupportActionBar().setTitle("Reply Now");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        property_id=(TextView)findViewById(R.id.property_id);
        property_name=(TextView)findViewById(R.id.property_name);
        et_msg=(EditText)findViewById(R.id.et_msg);

        property_id.setText("Requested Id  :"+getIntent().getStringExtra("bid"));
        property_name.setText("Email  :"+getIntent().getStringExtra("uname"));

        btn_book_now=(Button)findViewById(R.id.btn_book_now);
        btn_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                serverData();
            }
        });
    }

    public  void serverData() {
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.reply(getIntent().getStringExtra("bid"),getIntent().getStringExtra("uname"),et_msg.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().message.equals("true")) {
                    Toast.makeText(ReplyActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Log.i("msg", "" + response.body().message);
                    finish();
                } else {
                    Toast.makeText(ReplyActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
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