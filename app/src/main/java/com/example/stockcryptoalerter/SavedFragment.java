package com.example.stockcryptoalerter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SavedFragment extends Fragment {

    private View v;

    public SavedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_saved, container, false);

        // Inflate the layout for this fragment
        return v;
    }
}