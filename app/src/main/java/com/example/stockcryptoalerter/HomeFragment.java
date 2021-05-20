package com.example.stockcryptoalerter;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements CryptoRecyclerViewAdapter.ItemClickListener {

    private View v;
    private RecyclerView recyclerView ;
    private List<Crypto> cryptoList = new ArrayList<>();;
    private RequestQueue queue, queue2;
    private Uri uri = null;
    //private List<Crypto> cryptoList;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        EditText mSearchField = v.findViewById(R.id.search_field);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_crypto);
        final CryptoRecyclerViewAdapter cryptoRecyclerAdapter = new CryptoRecyclerViewAdapter(getContext(), cryptoList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cryptoRecyclerAdapter);



        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });


        return v;
    }

    //For data only
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

    }

    private void getData() {
        queue = Volley.newRequestQueue(getActivity());
        String coinListURL = "https://min-api.cryptocompare.com/data/all/coinlist?api_key=0519cb4b78a8227002870fb20faa3365adb18e81d51905247478154557d23466&summary=true";

        //Request Ticker, FullName, and the Image URL from the api
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, coinListURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject data_dict = response.getJSONObject("Data");
                            JSONArray keys = data_dict.names();

                            for (int i = 0; i < keys.length(); i++) {
                                String key = keys.getString(i);
                                JSONObject crypto_data = data_dict.getJSONObject(key);
                                String crypto_ticker = crypto_data.getString("Symbol");
                                String crypto_full_name = crypto_data.getString("FullName");
                                String crypto_image_url = crypto_data.getString("ImageUrl");

                                cryptoList.add(new Crypto(crypto_ticker, crypto_full_name, crypto_image_url));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("FUCKKKK");

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        queue.add(request);
    }

    @Override
    public void onItemClick(Crypto c) {
        Fragment infoFragment = CryptoInfoFragment.newInstance(c.getTicker(), c.getName(), c.getImageURL());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //.replace(R.id.fragment_container, infoFragment, "info_fragment");
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("home_fragment"));
        transaction.add(R.id.fragment_container, infoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}