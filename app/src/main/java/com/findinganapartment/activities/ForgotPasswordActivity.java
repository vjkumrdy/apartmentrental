package com.findinganapartment.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText et_email,et_otp,et_pwd;
    Button btn_submit,btn_otp,btn_pwd;
    SharedPreferences sharedPreferences;
    String uname;
    String sendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setTitle("Forget Password");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");

        btn_submit=(Button)findViewById(R.id.btn_submit);
        et_email=(EditText)findViewById(R.id.et_email);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your Email",Toast.LENGTH_LONG).show();
                    return;
                }
                //submitdata();
            }
        });

    }
    public  void submitdata()
    {
        String email=et_email.getText().toString();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.forgotPassword(email);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().message.equals("true")) {
                    Toast.makeText(ForgotPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Log.i("msg", "" + response.body().message);
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
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
