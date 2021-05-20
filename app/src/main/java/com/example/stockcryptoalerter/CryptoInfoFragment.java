package com.example.stockcryptoalerter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CryptoInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CryptoInfoFragment extends Fragment {

    private View v;
    private RequestQueue queue;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    public CryptoInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CryptoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CryptoInfoFragment newInstance(String param1, String param2, String param3) {
        CryptoInfoFragment fragment = new CryptoInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_crypto_info, container, false);
        TextView fcName = (TextView) v.findViewById(R.id.fc_crypto_name);
        fcName.setText(mParam2);
        ImageView cryptoImage = (ImageView) v.findViewById(R.id.fc_crypto_image) ;
        Glide.with(getActivity())
                .load(mParam3)
                .centerCrop()
                .placeholder(R.drawable.alarm)
                .into(cryptoImage);
        getData();
        //refresh(1000);
        return v;
    }

    private void getData() {
        queue = Volley.newRequestQueue(getActivity());
        String currency = "USD";
        String priceURL = "https://min-api.cryptocompare.com/data/price?fsym="+mParam1+"&tsyms="+currency;
        System.out.println(priceURL);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, priceURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String priceObject = response.getString("USD");
                            BigDecimal decPrice = new BigDecimal(priceObject);
                            TextView coinPrice = (TextView) getView().findViewById(R.id.fc_crypto_price);
                            coinPrice.setText(decPrice.toString());

                        } catch (JSONException e) {
                            TextView coinPrice = (TextView) getView().findViewById(R.id.fc_crypto_price);
                            coinPrice.setText("Coin not available yet!");
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView coinPrice = (TextView) getView().findViewById(R.id.fc_crypto_price);
                coinPrice.setText("Server not reachable!");
                error.printStackTrace();

            }
        });
        queue.add(request);
    }

    public void refresh (int milliseconds) {
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getData();
            }
        };
        handler.postDelayed(runnable, milliseconds);
    }
}