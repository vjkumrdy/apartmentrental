package com.findinganapartment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.findinganapartment.R;

public class WishListFragment extends Fragment {
    View view;

    public static WishListFragment wishListFragment() {
        WishListFragment fragment = new WishListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");


        return view;
    }
}