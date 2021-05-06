package com.example.stockcryptoalerter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView ;
    private List<Crypto> cryptoList = new ArrayList<>();;
    private RequestQueue queue;
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
        final CryptoRecyclerViewAdapter cryptoRecyclerAdapter = new CryptoRecyclerViewAdapter(getContext(), cryptoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cryptoRecyclerAdapter);



        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //cryptoRecyclerAdapter.getFilter().filter(s);



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
        getData();
        //cryptoList = new ArrayList<>();
        //cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));
        //getData();

        //EditText mSearchField = get
        //recycler view
        //resultList = v.findViewById(R.id.recycler_view_crypto);
        //mSearchField = (EditText) getView().findViewById(R.id.action_search);

    }

    private void getData() {
        queue = Volley.newRequestQueue(getActivity());
        String url = "https://min-api.cryptocompare.com/data/all/coinlist?api_key=0519cb4b78a8227002870fb20faa3365adb18e81d51905247478154557d23466?summary=true";
        //cryptoList.add(new Crypto("Bitcoin", "BTC", "60000", R.drawable.alarm));


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject data_dict = response.getJSONObject("Data");
                            JSONArray keys = data_dict.names();
                            //String crypto_names = data_dict.getString("BTC");

                            //JSONObject crypto_name = data_dict.getJSONObject("BTC");
                            //String crypto_full_name = crypto_name.getString("FullName");
                            //cryptoList.add(new Crypto(crypto_full_name, "cryptoTicker", "60000", R.drawable.alarm));


                            for (int i = 0; i < keys.length(); i++) {
                                String key = keys.getString(i);
                                JSONObject crypto_data = data_dict.getJSONObject(key);
                                String crypto_full_name = crypto_data.getString("FullName");
                                String crypto_ticker = crypto_data.getString("Symbol");

                                cryptoList.add(new Crypto(crypto_full_name, "Ok", "60000", R.drawable.alarm));
                            }
                            //JSONObject data_dict = jsonObject.getJSONObject("Data");
                            //String cryptoName =jsonObject.getString("Response");
                            //String cryptoPrice =data.getString("Name");
                            //String cryptoTicker =data.getString("Symbol");
                            //cryptoList.add(new Crypto("sucess", "cryptoTicker", "60000", R.drawable.alarm));




                            /*for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject data = jsonObject.getJSONObject("42");
                                String cryptoName =data.getString("Name");
                                //String cryptoPrice =data.getString("Name");
                                String cryptoTicker =data.getString("Symbol");
                                cryptoList.add(new Crypto(cryptoName, cryptoTicker, "60000", R.drawable.alarm));
                            }*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                            cryptoList.add(new Crypto("Fuckkk", "cryptoTicker", "60000", R.drawable.alarm));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                cryptoList.add(new Crypto("sucess", "cryptoTicker", "60000", R.drawable.alarm));
            }
        });
        queue.add(request);
    }
}