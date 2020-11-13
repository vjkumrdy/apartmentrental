package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.findinganapartment.R;
import com.findinganapartment.adapters.SliderAdapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.GetPhotosPojo;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOfProperties extends AppCompatActivity {
    ProgressDialog progressDialog;
    ImageView image_view;
    TextView tv_price,text_beds,text_baths,text_sq_feet,tv_pets,tv_description,tv_property_name,tv_location,tv_type,tv_apart_type,tv_time_spam;
    Button btn_book_now;
    SliderView sliderView;
    SliderAdapter sliderAdapter;
    List<GetPhotosPojo> a1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details);

        getSupportActionBar().setTitle("Property Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_price=(TextView)findViewById(R.id.tv_price);
        text_beds=(TextView)findViewById(R.id.text_beds);
        text_baths=(TextView)findViewById(R.id.text_baths);
        text_sq_feet=(TextView)findViewById(R.id.text_sq_feet);
        tv_pets=(TextView)findViewById(R.id.tv_pets);
        tv_description=(TextView)findViewById(R.id.tv_description);
        tv_time_spam=(TextView)findViewById(R.id.tv_time_spam);
        tv_property_name=(TextView)findViewById(R.id.tv_property_name);
        tv_location=(TextView)findViewById(R.id.tv_location);
        tv_type=(TextView)findViewById(R.id.tv_type);
        tv_apart_type=(TextView)findViewById(R.id.tv_apart_type);
//        image_view=(ImageView)findViewById(R.id.image_view);
//        Glide.with(this).load(getIntent().getStringExtra("image")).into(image_view);
       tv_price.setText(getIntent().getStringExtra("price")+"$"+"/"+getIntent().getStringExtra("per"));

        tv_property_name.setText(" - "+getIntent().getStringExtra("pname"));
        tv_location.setText("Location :"+getIntent().getStringExtra("location"));
        text_beds.setText(getIntent().getStringExtra("beds"));
        text_baths.setText(getIntent().getStringExtra("bath"));
        text_sq_feet.setText(getIntent().getStringExtra("area_sq_ft"));
        tv_pets.setText(getIntent().getStringExtra("pets"));
        tv_description.setText(getIntent().getStringExtra("description"));
        tv_type.setText("Type  :"+getIntent().getStringExtra("type"));
        tv_apart_type.setText(getIntent().getStringExtra("property_typE"));

       btn_book_now=(Button)findViewById(R.id.btn_book_now);
        btn_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailsOfProperties.this, messagingactivity.class);
                intent.putExtra("pid",getIntent().getStringExtra("pid"));
                intent.putExtra("from",getIntent().getStringExtra("username"));
                intent.putExtra("to",getIntent().getStringExtra("owner"));
                startActivity(intent);

            }
        });

        sliderView = (SliderView) findViewById(R.id.imageSlider);
        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        a1 = new ArrayList<>();
        serverData();
    }

    public void serverData(){
        progressDialog = new ProgressDialog(DetailsOfProperties.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetPhotosPojo>> call = service.getphotos(getIntent().getStringExtra("pid"));

        call.enqueue(new Callback<List<GetPhotosPojo>>() {
            @Override
            public void onResponse(Call<List<GetPhotosPojo>> call, Response<List<GetPhotosPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(DetailsOfProperties.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    sliderAdapter = new SliderAdapter(DetailsOfProperties.this,a1);
                    sliderView.setSliderAdapter(sliderAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<GetPhotosPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailsOfProperties.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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
