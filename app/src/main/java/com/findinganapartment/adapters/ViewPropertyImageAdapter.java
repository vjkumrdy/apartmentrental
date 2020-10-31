package com.findinganapartment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.models.GetPhotosPojo;

import java.util.List;

public class ViewPropertyImageAdapter extends RecyclerView.Adapter<ViewPropertyImageAdapter.MyviewHolder> {

    Context context;
    List<GetPhotosPojo> a1;

    public ViewPropertyImageAdapter(Context context, List<GetPhotosPojo> categoty) {
        this.context = context;
        this.a1 = categoty;
    }

    public void setMovieList(List<GetPhotosPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_view_property_images, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int pos) {


        //Glide.with(context).load(a1.get(pos).getPhoto()).into(holder.im_view);


    }

    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView im_view;


        public MyviewHolder(View itemView) {
            super(itemView);

            //im_view=(ImageView)itemView.findViewById(R.id.im_view);



        }
    }

}