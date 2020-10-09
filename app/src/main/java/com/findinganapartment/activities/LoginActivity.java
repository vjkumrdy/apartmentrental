package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tv_sign_up,tv_forget_pass;
    Button login_btn,login_guest,login_admin;
    EditText et_email,et_password;
    CheckBox cb_tenant,cb_land_lord;
    ProgressDialog pd;
    String str;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");


        et_email=(EditText)findViewById(R.id.et_email);
        et_password=(EditText)findViewById(R.id.et_password);


        cb_tenant=(CheckBox)findViewById(R.id.cb_tenant);
        cb_land_lord=(CheckBox)findViewById(R.id.cb_land_lord);

        login_guest=(Button)findViewById(R.id.login_guest);
        login_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, AllPropertiesActivity.class);
                startActivity(intent);

            }
        });
        tv_forget_pass=(TextView)findViewById(R.id.tv_forget_pass);
        tv_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);

            }
        });

        tv_sign_up=(TextView)findViewById(R.id.tv_sign_up);
        login_btn=(Button) findViewById(R.id.login_btn);
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        login_admin=(Button)findViewById(R.id.login_admin);
        login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,AdminLoginActivity.class));

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkingTenantLandlord();
               /* if (cb_tenant.isChecked()){
                    if(et_email.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Email", LENGTH_SHORT).show();
                        return;
                    }

                    if(et_password.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Password", LENGTH_SHORT).show();
                        return;
                    }
                    tenantloginData();

                }
                else if(cb_land_lord.isChecked()){
                    *//*if(et_email.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Email", LENGTH_SHORT).show();
                        return;
                    }

                    if(et_password.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Password", LENGTH_SHORT).show();
                        return;
                    }*//*
                    //Toast.makeText(LoginActivity.this, "Check Box Clicked", LENGTH_SHORT).show();
                    landLordloginData();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Select Login Type above", LENGTH_SHORT).show();
                }*/
            }
        });


    }
    public void checkingTenantLandlord(){
        if(cb_tenant.isChecked()){
            str="Tenant";
            tenantloginData();
        }
        else{
            str="Land Lord";
            landLordloginData();
        }
    }
    public  void tenantloginData() {
        pd= new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.userLogin(et_email.getText().toString(),et_password.getText().toString(),str);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("uname",et_email.getText().toString());
                    et.commit();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    //Toast.makeText(LoginActivity.this, "Successfully login", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public  void landLordloginData() {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.landlordlogin(et_email.getText().toString(), et_password.getText().toString(),str);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et = sharedPreferences.edit();
                    et.putString("uname", et_email.getText().toString());
                    et.commit();
                    startActivity(new Intent(LoginActivity.this, LandLordDashboardActivity.class));
                    //Toast.makeText(LoginActivity.this, "Successfully login", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
