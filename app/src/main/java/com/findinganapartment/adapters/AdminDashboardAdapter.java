package com.findinganapartment.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.findinganapartment.R;
import com.findinganapartment.activities.AdminDashBoardActivity;
import com.findinganapartment.activities.DetailsOfProperties;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.PropertyPojo;
import com.findinganapartment.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  AdminDashboardAdapter extends RecyclerView.Adapter<AdminDashboardAdapter.MyviewHolder> {

    Context context;
    List<PropertyPojo> a1;

    public AdminDashboardAdapter(Context context, List<PropertyPojo> categoty) {
        this.context = context;
        this.a1 = categoty;
    }

    public void setMovieList(List<PropertyPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_admindash_board, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_price.setText(a1.get(pos).getP_price()+"$" +"/"+a1.get(pos).getPer());
        holder.tv_time_spam.setText(" - "+a1.get(pos).getP_name());
        holder.tv_beds.setText(a1.get(pos).getP_beds());
        holder.tv_pets.setText(a1.get(pos).getP_pets());
        holder.tv_baths.setText(a1.get(pos).getP_bath());
        holder.tv_sq_feet.setText(a1.get(pos).getP_area());
        holder.tv_apart_type.setText(a1.get(pos).getP_type());
        holder.tv_status.setText("Current Status : "+a1.get(pos).getA_status());
        Glide.with(context).load(a1.get(pos).getP_pic()).into(holder.image_view);

        holder.img_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverData(a1.get(pos).getPid(),"Verified");
              //  Toast.makeText(context, ""+a1.get(pos).getPid()+a1.get(pos).getPid(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.img_deney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverData(a1.get(pos).getPid(),"Deny");

            }
        });


        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsOfProperties.class);
                intent.putExtra("image",a1.get(pos).getP_pic());
                intent.putExtra("price",a1.get(pos).getP_price()+"$");
                intent.putExtra("beds",a1.get(pos).getP_beds());
                intent.putExtra("bath",a1.get(pos).getP_bath());
                intent.putExtra("area_sq_ft",a1.get(pos).getP_area());
                intent.putExtra("pets",a1.get(pos).getP_pets());
                context.startActivity(intent);
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
        TextView tv_apart_type,tv_price,tv_time_spam,tv_beds,tv_baths,tv_sq_feet,tv_pets,tv_status;
        ImageView image_view,img_accept,img_deney;



        public MyviewHolder(View itemView) {
            super(itemView);

            tv_price= (TextView) itemView.findViewById(R.id.tv_price);
            tv_apart_type= (TextView) itemView.findViewById(R.id.tv_apart_type);
            tv_time_spam= (TextView) itemView.findViewById(R.id.tv_time_spam);
            tv_beds= (TextView) itemView.findViewById(R.id.tv_beds);
            tv_baths= (TextView) itemView.findViewById(R.id.tv_baths);
            tv_sq_feet= (TextView) itemView.findViewById(R.id.tv_sq_feet);
            tv_pets= (TextView) itemView.findViewById(R.id.tv_pets);
            tv_status= (TextView) itemView.findViewById(R.id.tv_status);
            image_view=(ImageView)itemView.findViewById(R.id.image_view);
            img_accept=(ImageView)itemView.findViewById(R.id.img_accept);
            img_deney=(ImageView)itemView.findViewById(R.id.img_deney);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_apart_type.setTypeface(custom_font);
            tv_price.setTypeface(custom_font);
            tv_time_spam.setTypeface(custom_font);
            tv_baths.setTypeface(custom_font);
            tv_beds.setTypeface(custom_font);
            tv_pets.setTypeface(custom_font);
            tv_sq_feet.setTypeface(custom_font);
            tv_status.setTypeface(custom_font);

        }
    }
    ProgressDialog progressDialog;
    public void serverData(String id, String status){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

      //  Toast.makeText(context, ""+id+status, Toast.LENGTH_SHORT).show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.updatestatus(id,status);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(context,"Server issue", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(context, AdminDashBoardActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,"Status updated successfully", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}