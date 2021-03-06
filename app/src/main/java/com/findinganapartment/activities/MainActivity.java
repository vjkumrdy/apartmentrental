package com.findinganapartment.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.findinganapartment.R;
import com.findinganapartment.fragment.HomeFragment;
import com.findinganapartment.fragment.ProfileFragment;
import com.findinganapartment.fragment.SettingsFragment;
import com.findinganapartment.fragment.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation();
        navigationView();
    }
    private void bottomNavigation() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_item1);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = HomeFragment.homeFragment();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ProfileFragment.profileFragment();
                                break;
                            case R.id.action_item3:
                                selectedFragment = WishListFragment.wishListFragment();
                                break;
                            case R.id.item_settings:
                                selectedFragment = SettingsFragment.settingsFragment();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.homeFragment());
        transaction.commit();
    }
    private void navigationView(){
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                        Intent home=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;


                    case R.id.myprofile:
                        Intent intent=new Intent(getApplicationContext(), EditYourProfileActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.property_list:
                        Intent prop_list=new Intent(getApplicationContext(), AllPropertiesActivity.class);
                        startActivity(prop_list);
                        break;


                    case R.id.search_properties:
                        Intent search_prop=new Intent(getApplicationContext(), SearchPropertiesActivity.class);
                        startActivity(search_prop);
                        break;

                    case R.id.myrequest_list:
                        Intent myrequest_list=new Intent(getApplicationContext(), TenantRequestActivity.class);
                        startActivity(myrequest_list);
                        break;


                    case R.id.wish_list:
//                        Intent wish_list=new Intent(getApplicationContext(), EditYourProfileActivity.class);
//                        startActivity(wish_list);
                        Toast.makeText(MainActivity.this,"Comming Soon...", Toast.LENGTH_LONG).show();
                        break;



                    case R.id.logout:
                        Intent logout=new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(logout);
                        finish();
                        break;

                    default:
                        return true;
                }
                dl.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            alertDiolouge();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void alertDiolouge(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setTitle("Alert !!!");
        builder1.setMessage("Do you want to close the Application.");  //message we want to show the end user
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel(); //cancle the alert dialog box
                        finish();//finish the process
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