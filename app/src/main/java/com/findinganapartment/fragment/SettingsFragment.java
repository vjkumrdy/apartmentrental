package com.findinganapartment.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.findinganapartment.R;
import com.findinganapartment.Utils;
import com.findinganapartment.activities.SearchFilterActivity;
import com.findinganapartment.adapters.SearchPropertiesAdapter;
import com.findinganapartment.models.PropertyPojo;

import java.util.List;

public class SettingsFragment extends Fragment {
    RecyclerView property_recyclerView;
    List<PropertyPojo> a1;
    SearchPropertiesAdapter searchPropertiesAdapter;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session;
    View view;
    EditText searchView;
    TextView et_search,et_search1;
    Spinner spin_price,spin_ptype,spin_beds,spin_location;
    int pricenum;

    public static SettingsFragment settingsFragment() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Search");

        sharedPreferences = getActivity().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("uname", "def-val");

        spin_ptype=(Spinner)view.findViewById(R.id.spin_ptype);
        spin_beds=(Spinner)view.findViewById(R.id.spin_beds);
        spin_location=(Spinner)view.findViewById(R.id.spin_location);

        et_search=(TextView)view.findViewById(R.id.et_search);

        et_search1=(TextView)view.findViewById(R.id.et_search1);


        /*SeekBar price=(SeekBar)view.findViewById(R.id.seekBar);
        price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //Toast.makeText(getContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
                 pricenum=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });*/

        SeekBar seekBar = (SeekBar)view.findViewById(R.id.seekBar);
        seekBar.setProgress(1000);
        seekBar.incrementProgressBy(1000);
        seekBar.setMax(10000);

        et_search.setText(et_search.getText().toString().trim());

        et_search1.setText("Selected Range is : "+et_search1.getText().toString().trim()+"$");


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 1000;
                progress = progress * 1000;
                //Toast.makeText(getContext(), ""+progress, Toast.LENGTH_SHORT).show();
                et_search.setText(String.valueOf(progress));
                et_search1.setText("Selected Range is : "+String.valueOf(progress)+"$");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button login_sumbit=(Button)view.findViewById(R.id.login_sumbit);
        login_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SearchFilterActivity.class);
                intent.putExtra("ptype", spin_ptype.getSelectedItem().toString());
                intent.putExtra("pbeds", spin_beds.getSelectedItem().toString());
                intent.putExtra("location", spin_location.getSelectedItem().toString());
                intent.putExtra("price", et_search.getText().toString());
                startActivity(intent);
            }
        });


        return view;
    }

}