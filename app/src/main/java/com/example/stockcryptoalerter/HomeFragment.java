package com.example.stockcryptoalerter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private List<Crypto> cryptoList;
    //private List<Crypto> cryptoList;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        EditText mSearchField = v.findViewById(R.id.search_field);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_crypto);
        final CryptoRecyclerViewAdapter recyclerAdapter = new CryptoRecyclerViewAdapter(getContext(), cryptoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                recyclerAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });


        return v;
    }

    //For data only
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cryptoList = new ArrayList<>();
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));
        cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        cryptoList.add(new Crypto("Euthereum", "ETH", "6060", R.drawable.alarm));
        cryptoList.add(new Crypto("Binance", "Binance", "660", R.drawable.alarm));

        //EditText mSearchField = get
        //recycler view
        //resultList = v.findViewById(R.id.recycler_view_crypto);
        //mSearchField = (EditText) getView().findViewById(R.id.action_search);
        //if (!Python.isStarted()) {
        //  Python.start(new AndroidPlatform(getContext()));
        //}
        //Python py = Python.getInstance();
        //PyObject pyobject = py.getModule("");
    }
}