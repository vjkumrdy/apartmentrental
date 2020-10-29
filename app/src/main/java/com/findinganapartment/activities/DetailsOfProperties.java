package com.findinganapartment.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.findinganapartment.R;

public class DetailsOfProperties extends AppCompatActivity {
    ImageView image_view;
    TextView tv_price,text_beds,text_baths,text_sq_feet,tv_pets,tv_description,tv_time_spam;
    Button btn_book_now;
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
        image_view=(ImageView)findViewById(R.id.image_view);
        Glide.with(this).load(getIntent().getStringExtra("image")).into(image_view);
        tv_price.setText(getIntent().getStringExtra("price")+"$");
        tv_time_spam.setText(getIntent().getStringExtra("location"));
        text_beds.setText(getIntent().getStringExtra("beds"));
        text_baths.setText(getIntent().getStringExtra("bath"));
        text_sq_feet.setText(getIntent().getStringExtra("area_sq_ft"));
        tv_pets.setText(getIntent().getStringExtra("pets"));

        tv_description.setText(getIntent().getStringExtra("des"));

      /*  btn_book_now=(Button)findViewById(R.id.btn_book_now);
        btn_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Details.this, BookingActivity.class);
                intent.putExtra("pid",getIntent().getStringExtra("pid"));
                intent.putExtra("uname",getIntent().getStringExtra("username"));
                startActivity(intent);

            }
        });*/


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
