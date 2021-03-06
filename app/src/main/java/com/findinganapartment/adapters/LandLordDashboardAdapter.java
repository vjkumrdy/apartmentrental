package com.findinganapartment.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.findinganapartment.R;
import com.findinganapartment.activities.ViewPropertyImagesActivity;
import com.findinganapartment.models.PropertyPojo;

import java.util.List;

public class LandLordDashboardAdapter extends RecyclerView.Adapter<LandLordDashboardAdapter.MyviewHolder> {

    Context context;
    List<PropertyPojo> a1;
    String session;

    public LandLordDashboardAdapter(Context context, List<PropertyPojo> categoty, String session) {
        this.context = context;
        this.a1 = categoty;
        this.session=session;
    }

    public void setMovieList(List<PropertyPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_land_lorddashboard, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_price.setText(a1.get(pos).getP_price()+"$" +"/"+a1.get(pos).getPer());
        holder.tv_property_name.setText(" - "+a1.get(pos).getP_name());
        holder.tv_beds.setText(a1.get(pos).getP_beds());
        holder.tv_baths.setText(a1.get(pos).getP_bath());
        holder.tv_pets.setText(a1.get(pos).getP_pets());
        holder.tv_sq_feet.setText(a1.get(pos).getP_area());
        holder.tv_apart_type.setText(a1.get(pos).getP_type());
        holder.tv_offer.setText(a1.get(pos).getP_owner());
        holder.tv_location.setText("Location :"+a1.get(pos).getLocation());
        holder.tv_type.setText("Type  :"+a1.get(pos).getType());
        Glide.with(context).load(a1.get(pos).getP_pic()).into(holder.image_view);


        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewPropertyImagesActivity.class);
                intent.putExtra("image",a1.get(pos).getP_pic());
                intent.putExtra("pid",a1.get(pos).getPid());
                intent.putExtra("property_typE",a1.get(pos).getP_type());
                intent.putExtra("price",a1.get(pos).getP_price());
                intent.putExtra("pname",a1.get(pos).getP_name());
                intent.putExtra("beds",a1.get(pos).getP_beds());
                intent.putExtra("bath",a1.get(pos).getP_bath());
                intent.putExtra("area_sq_ft",a1.get(pos).getP_area());
                intent.putExtra("pets",a1.get(pos).getP_pets());
                intent.putExtra("location",a1.get(pos).getLocation());
                intent.putExtra("description",a1.get(pos).getDescription());
                intent.putExtra("type",a1.get(pos).getType());
                intent.putExtra("owner",a1.get(pos).getP_owner());
                intent.putExtra("username",session);
                intent.putExtra("per",a1.get(pos).getPer());

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
        TextView tv_apart_type,tv_price,tv_property_name,tv_beds,tv_baths,tv_sq_feet,tv_pets,tv_offer,tv_location,tv_type;
        ImageView image_view;


        public MyviewHolder(View itemView) {
            super(itemView);

            tv_price= (TextView) itemView.findViewById(R.id.tv_price);
            tv_apart_type= (TextView) itemView.findViewById(R.id.tv_apart_type);
            tv_property_name= (TextView) itemView.findViewById(R.id.tv_property_name);
            tv_beds= (TextView) itemView.findViewById(R.id.tv_beds);
            tv_baths= (TextView) itemView.findViewById(R.id.tv_baths);
            tv_sq_feet= (TextView) itemView.findViewById(R.id.tv_sq_feet);
            tv_pets= (TextView) itemView.findViewById(R.id.tv_pets);
            tv_offer= (TextView) itemView.findViewById(R.id.tv_offer);
            tv_location= (TextView) itemView.findViewById(R.id.tv_location);
            tv_type= (TextView) itemView.findViewById(R.id.tv_type);
            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_apart_type.setTypeface(custom_font);
            tv_price.setTypeface(custom_font);
            tv_property_name.setTypeface(custom_font);
            tv_baths.setTypeface(custom_font);
            tv_beds.setTypeface(custom_font);
            tv_pets.setTypeface(custom_font);
            tv_sq_feet.setTypeface(custom_font);
            tv_location.setTypeface(custom_font);
            tv_type.setTypeface(custom_font);
            tv_offer.setTypeface(custom_font);




        }
    }
}