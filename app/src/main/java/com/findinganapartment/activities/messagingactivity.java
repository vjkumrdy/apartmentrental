package com.findinganapartment.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.adapters.messagesadapter;
import com.findinganapartment.api.ApiService;
import com.findinganapartment.api.RetroClient;
import com.findinganapartment.models.ResponseData;
import com.findinganapartment.models.msgs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class messagingactivity extends AppCompatActivity {
ApiService apiService;
String frm;
String eto;
String pid="3";
List<msgs> msg=new ArrayList<msgs>();

EditText msgtext;
    ProgressDialog pd;
    messagesadapter messagesadapter;
Button send;
    Runnable r;
     RecyclerView recyclerView;
    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagingactivity);
//        getSupportActionBar().setTitle("Chat");
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        msgtext=(EditText)findViewById(R.id.msgtext);
        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(msgtext.getText().toString().isEmpty())
                {
                    Toast.makeText(messagingactivity.this,"Please enter message", Toast.LENGTH_SHORT).show();
                }
                sendMessage(getIntent().getStringExtra("from"),getIntent().getStringExtra("to"),getIntent().getStringExtra("pid"));
            }
        });

//        Toast.makeText(messagingactivity.this,getIntent().getStringExtra("pid").toString()+getIntent().getStringExtra("from").toString()+getIntent().getStringExtra("to").toString(), Toast.LENGTH_SHORT).show();
        frm=getIntent().getStringExtra("from");
        eto=getIntent().getStringExtra("to");
        Log.d("checktool",frm+""+eto);
        recyclerView=findViewById(R.id.messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        pd = new ProgressDialog(getApplicationContext());
//        pd.setTitle("Please wait,Data is being loading.");
//        pd.show();

     messagesadapter=new messagesadapter(msg,frm, messagingactivity.this);
recyclerView.setAdapter(messagesadapter);

       r =new Runnable() {
            @Override
            public void run() {
                h.postDelayed(r,10000);
                getmessages();


            }
        };

        h.post(r);


    }

public void getmessages(){
    ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
    Call<List<msgs>> call = service.getchat(frm,eto,getIntent().getStringExtra("pid"));
    call.enqueue(new Callback<List<msgs>>() {
        @Override
        public void onResponse(Call<List<msgs>> call, Response<List<msgs>> response) {
            if(response.body()==null){
                Toast.makeText(messagingactivity.this,"No data found", Toast.LENGTH_SHORT).show();
            }else {
                // pd.dismiss();
                Log.d("testtt",response.toString());
                msg = response.body();
                messagesadapter.data(msg);
               //messagesadapter.notifyDataSetChanged();
             recyclerView.smoothScrollToPosition(msg.size()-1);

            }
        }

        @Override
        public void onFailure(Call<List<msgs>> call, Throwable t) {

        }
    });
}
    public  void sendMessage(final String frm, final String eto, final String pid) {
//        pd = new ProgressDialog(getApplicationContext());
//        pd.setTitle("Please wait, message sending.");
//        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.msglist(frm,eto,pid,msgtext.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                getmessages();

                if (response.body().message.equals("true")) {
                   // pd.dismiss();
                    Toast.makeText(messagingactivity.this, response.body().message, Toast.LENGTH_LONG).show();
                   // Log.i("msg", "" + response.body().message);

                   // finish();
                } else {
                    Toast.makeText(messagingactivity.this, response.body().message, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}