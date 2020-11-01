package com.findinganapartment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.models.msgs;

import java.util.ArrayList;
import java.util.List;

public class messagesadapter extends RecyclerView.Adapter<messagesadapter.MessagesViewHolder> {

    private final Context context;

    public messagesadapter(List<msgs> msg, String from, Context context){
        this.from=from;
        this.context=context;
        this.msg=msg;

    }
    List<msgs> msg=new ArrayList<>();
    String from;
    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messagesview, parent, false);
        return new MessagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
msgs m=msg.get(position);
if(m.getFrom().equals(from)){
    holder.msgfield.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
    holder.msgfield.setGravity(Gravity.LEFT);
    holder.msgfield.setTextColor(Color.parseColor("#ffffff"));

}
holder.msgfield.setText(m.getMessage());
    }

    public void data(List<msgs> msg){
        this.msg=msg;
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return msg.size();
    }

    class MessagesViewHolder extends  RecyclerView.ViewHolder{
TextView msgfield;
        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
msgfield=itemView.findViewById(R.id.msgs);
        }

    }


}

