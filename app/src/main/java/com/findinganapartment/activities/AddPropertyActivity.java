package com.findinganapartment.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.models.ResponseData;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPropertyActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    EditText et_property_name,et_property_area,et_property_price,et_property_desc;
    Spinner spin_property_type,spin_property_beds,spin_bath_rooms,spin_pets,spin_location,spin_per;
    Button property_image,btn_submit;
    ProgressDialog pd;
    private static final String TAG = AddPropertyActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://findingaapartment.com/";
    private Uri uri;
    String session;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        getSupportActionBar().setTitle("Add Property");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("uname", "def-val");

        et_property_name=(EditText)findViewById(R.id.et_property_name);
        et_property_area=(EditText)findViewById(R.id.et_property_area);
        et_property_price=(EditText)findViewById(R.id.et_property_price);

        et_property_desc=(EditText)findViewById(R.id.et_property_desc);

        spin_property_type=(Spinner)findViewById(R.id.spin_property_type);
        spin_property_beds=(Spinner)findViewById(R.id.spin_property_beds);
        spin_bath_rooms=(Spinner)findViewById(R.id.spin_bath_rooms);
        spin_pets=(Spinner)findViewById(R.id.spin_pets);
        spin_location=(Spinner)findViewById(R.id.spin_location);
        spin_per=(Spinner)findViewById(R.id.spin_per);

        property_image=(Button)findViewById(R.id.property_image);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        property_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImageToServer();
            }

        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, AddPropertyActivity.this);
    }
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, AddPropertyActivity.this);
                file = new File(filePath);

            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, AddPropertyActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    private void uploadImageToServer(){
        pd=new ProgressDialog(AddPropertyActivity.this);
        pd.setTitle("Loading");
        pd.show();
        Map<String, String> map = new HashMap<>();
        map.put("pname",et_property_name.getText().toString());
        map.put("ptype",spin_property_type.getSelectedItem().toString());
        map.put("pbeds",spin_property_beds.getSelectedItem().toString());
        map.put("pbath",spin_bath_rooms.getSelectedItem().toString());
        map.put("parea",et_property_area.getText().toString());
        map.put("des",et_property_desc.getText().toString());
        map.put("per",spin_per.getSelectedItem().toString());
        map.put("ppets",spin_pets.getSelectedItem().toString());
        map.put("pprice",et_property_price.getText().toString());
        map.put("location",spin_location.getSelectedItem().toString());
        map.put("powner",session);


        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService uploadImage = retrofit.create(ApiService.class);
        Call<ResponseData> fileUpload = uploadImage.add_property(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                Toast.makeText(AddPropertyActivity.this, "Property Added successfully. ", Toast.LENGTH_LONG).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddPropertyActivity.this, "Error"+t.getMessage(), Toast.LENGTH_LONG).show();
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
