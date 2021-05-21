package com.example.stockcryptoalerter;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements CryptoRecyclerViewAdapter.ItemClickListener {

    private View v;
    private RecyclerView recyclerView ;
    private List<Crypto> cryptoList = new ArrayList<>();
    private RequestQueue queue, queue2;
    private CryptoRecyclerViewAdapter cryptoRecyclerAdapter;
    private SearchView searchView;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }

    //For data only
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = v.findViewById(R.id.search_field);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_crypto);

        cryptoRecyclerAdapter = new CryptoRecyclerViewAdapter(getContext(), cryptoList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cryptoRecyclerAdapter);

        //Search Bar
        searchView.setIconifiedByDefault(false); //https://stackoverflow.com/questions/30455723/android-make-whole-search-bar-clickable
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cryptoRecyclerAdapter.filterText(query);

                //recyclerView.setAdapter(cryptoRecyclerAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cryptoRecyclerAdapter.filterText(newText);
                //recyclerView.setAdapter(cryptoRecyclerAdapter);
                return true;
            }
        });

        return v;
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


                                if (!crypto_data.getString("Symbol").equals("IBP")) { //IBP does not have imagurl
                                    String crypto_image_url = crypto_data.getString("ImageUrl");
                                    String crypto_ticker = crypto_data.getString("Symbol");
                                    String crypto_full_name = crypto_data.getString("FullName");
                                    cryptoList.add(new Crypto(crypto_ticker, crypto_full_name, crypto_image_url));
                                }
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