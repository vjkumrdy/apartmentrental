package com.findinganapartment.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.findinganapartment.R;
import com.findinganapartment.activities.EditMyPropertiesActivity;
import com.findinganapartment.activities.ImageUploadActivity;
import com.findinganapartment.activities.MyPropertiesActivity;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.PropertyPojo;
import com.findinganapartment.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPropertiesAdapter extends RecyclerView.Adapter<MyPropertiesAdapter.MyviewHolder> {

    Context context;
    List<PropertyPojo> a1;

    public MyPropertiesAdapter(Context context, List<PropertyPojo> categoty) {
        this.context = context;
        this.a1 = categoty;
    }

    public void setMovieList(List<PropertyPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_my_properties, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {


        Glide.with(context).load(a1.get(pos).getP_pic()).into(holder.image_view);
        holder.tv_price.setText(a1.get(pos).getP_price()+"$" +"/"+a1.get(pos).getPer());
        holder.tv_time_spam.setText(" -  "+a1.get(pos).getP_name());
        holder.tv_beds.setText(a1.get(pos).getP_beds());
        holder.tv_baths.setText(a1.get(pos).getP_bath());
        holder.tv_pets.setText(a1.get(pos).getP_pets());
        holder.tv_sq_feet.setText(a1.get(pos).getP_area());
        holder.tv_location.setText("Location :"+a1.get(pos).getLocation());
        holder.tv_apart_type.setText(a1.get(pos).getP_type());

        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ImageUploadActivity.class);
                intent.putExtra("id",a1.get(pos).getPid());
                context.startActivity(intent);


            }
        });


        holder.edit_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, EditMyPropertiesActivity.class);
                intent.putExtra("image",a1.get(pos).getP_pic());
                intent.putExtra("id",a1.get(pos).getPid());
                intent.putExtra("price",a1.get(pos).getP_price());
                intent.putExtra("property_name",a1.get(pos).getP_name());
                intent.putExtra("description",a1.get(pos).getDescription());
                intent.putExtra("location",a1.get(pos).getLocation());
                intent.putExtra("beds",a1.get(pos).getP_beds());
                intent.putExtra("bath",a1.get(pos).getP_bath());
                intent.putExtra("pets",a1.get(pos).getP_pets());
                intent.putExtra("area",a1.get(pos).getP_area());
                intent.putExtra("property_type",a1.get(pos).getP_type());
                context.startActivity(intent);

            }
        });
        holder.delete_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDioluge(a1.get(pos).getPid());

            }
        });

    }

    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_apart_type,tv_price,tv_time_spam,tv_beds,tv_baths,tv_sq_feet,tv_pets,tv_offer,tv_location;
        ImageView image_view;
        CardView My_properties;
        Button edit_property,delete_property;


        public MyviewHolder(View itemView) {
            super(itemView);

            tv_price= (TextView) itemView.findViewById(R.id.tv_price);
            tv_apart_type= (TextView) itemView.findViewById(R.id.tv_apart_type);
            tv_time_spam= (TextView) itemView.findViewById(R.id.tv_time_spam);
            tv_beds= (TextView) itemView.findViewById(R.id.tv_beds);
            tv_baths= (TextView) itemView.findViewById(R.id.tv_baths);
            tv_sq_feet= (TextView) itemView.findViewById(R.id.tv_sq_feet);
            tv_pets= (TextView) itemView.findViewById(R.id.tv_pets);
            tv_offer= (TextView) itemView.findViewById(R.id.tv_offer);
            tv_location= (TextView) itemView.findViewById(R.id.tv_location);
            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            My_properties=(CardView)itemView.findViewById(R.id.My_properties);

            edit_property=(Button)itemView.findViewById(R.id.edit_property);
            delete_property=(Button)itemView.findViewById(R.id.delete_property);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_apart_type.setTypeface(custom_font);
            tv_price.setTypeface(custom_font);
            tv_time_spam.setTypeface(custom_font);
            tv_baths.setTypeface(custom_font);
            tv_beds.setTypeface(custom_font);
            tv_pets.setTypeface(custom_font);
            tv_sq_feet.setTypeface(custom_font);
            tv_location.setTypeface(custom_font);

        }
    }
    ProgressDialog progressDialog;
    public void serverData(String id){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deleteproperty(id);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(context,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    context.startActivity(new Intent(context, MyPropertiesActivity.class));
                    Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void alertDioluge(final String id){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Alert !!!");
        builder1.setMessage("Do you want to Delete Property.");  //message we want to show the end user
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        serverData(id);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}