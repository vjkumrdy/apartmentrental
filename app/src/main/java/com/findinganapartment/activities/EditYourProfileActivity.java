package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.EditProfilePojo;
import com.findinganapartment.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditYourProfileActivity extends AppCompatActivity {
    EditText et_name,et_email,et_phno,et_pwd;
    ProgressDialog progressDialog;
    List<EditProfilePojo> a1;
    SharedPreferences sharedPreferences;
    String session;
    ResponseData a2;
    Button btn_update;
    ImageView image_view_user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("uname", "def-val");

        image_view_user=(ImageView)findViewById(R.id.image_view_user);
        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_phno=(EditText)findViewById(R.id.et_phno);
        et_pwd=(EditText)findViewById(R.id.et_pwd);

        btn_update=(Button) findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
        et_email.setText(session);


       progressDialog = new ProgressDialog(EditYourProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<EditProfilePojo>> call = service.getprofile(session);

        call.enqueue(new Callback<List<EditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<EditProfilePojo>> call, Response<List<EditProfilePojo>> response) {

                progressDialog.dismiss();
                a1 = response.body();
                // Toast.makeText(getApplicationContext(),""+response.body().size(),Toast.LENGTH_LONG).show();
                EditProfilePojo user = a1.get(0);

                et_name.setText(user.getName());
                et_email.setText(user.getEmail());
                et_phno.setText(user.getPhone());
                et_pwd.setText(user.getPassword());

                Glide.with(EditYourProfileActivity.this).load("http://findingaapartment.com/rental/"+a1.get(0).getPhoto()).into(image_view_user);
              //  Glide.with(getApplicationContext()).load(user.getPhoto()).into(image_view_user);

            }

            @Override
            public void onFailure(Call<List<EditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditYourProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
   }
    private void submitData() {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String phoneno= et_phno.getText().toString();
        String pwd = et_pwd.getText().toString();


        progressDialog = new ProgressDialog(EditYourProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        session = sharedPreferences.getString("user_name", "def-val");
        //Toast.makeText(EditYourProfileActivity.this, session, Toast.LENGTH_SHORT).show();


        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.update_user_profile(name,email,phoneno,pwd);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                progressDialog.dismiss();
                a2 = response.body();

                if (response.body().status.equals("true")) {
                    Toast.makeText(EditYourProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditYourProfileActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditYourProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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