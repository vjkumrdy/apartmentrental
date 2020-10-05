package com.findinganapartment.adapters;

import android.content.Context;
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
import com.findinganapartment.models.PropertyPojo;

import java.util.List;

public class AllPropertiesAdapter extends RecyclerView.Adapter<AllPropertiesAdapter.MyviewHolder> {

    Context context;
    List<PropertyPojo> a1;

    public AllPropertiesAdapter(Context context, List<PropertyPojo> categoty) {
        this.context = context;
        this.a1 = categoty;
    }

    public void setMovieList(List<PropertyPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_all_properties, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {

        holder.tv_price.setText(a1.get(pos).getP_price());
        holder.tv_time_spam.setText(" -  "+a1.get(pos).getP_name());
        holder.tv_beds.setText(a1.get(pos).getP_beds());
        holder.tv_baths.setText(a1.get(pos).getP_bath());

        holder.tv_pets.setText(a1.get(pos).getP_pets());
        holder.tv_sq_feet.setText(a1.get(pos).getP_area());
        holder.tv_apart_type.setText(a1.get(pos).getP_type());
        holder.tv_offer.setText(a1.get(pos).getP_owner());
        Glide.with(context).load(a1.get(pos).getP_pic()).into(holder.image_view);


/*
        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PropertyDetailsActivity.class);
                intent.putExtra("image",a1.get(pos).getImage());
                intent.putExtra("price",a1.get(pos).getPrice());
                intent.putExtra("address",a1.get(pos).getAddress());
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_apart_type,tv_price,tv_time_spam,tv_beds,tv_baths,tv_sq_feet,tv_pets,tv_offer;
        ImageView image_view;


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
            image_view=(ImageView)itemView.findViewById(R.id.image_view);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Medium.ttf");
            tv_apart_type.setTypeface(custom_font);
            tv_price.setTypeface(custom_font);
            tv_time_spam.setTypeface(custom_font);
            tv_baths.setTypeface(custom_font);
            tv_beds.setTypeface(custom_font);
            tv_pets.setTypeface(custom_font);
            tv_sq_feet.setTypeface(custom_font);
            tv_offer.setTypeface(custom_font);




        }
    }
}