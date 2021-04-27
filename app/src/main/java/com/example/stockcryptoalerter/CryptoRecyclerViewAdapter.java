package com.example.stockcryptoalerter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CryptoRecyclerViewAdapter extends RecyclerView.Adapter<CryptoRecyclerViewAdapter.MyViewHolder> implements Filterable {

    List<Crypto> data;
    List<Crypto> dataFiltered;
    Context context;

    public CryptoRecyclerViewAdapter(Context ct, List<Crypto> dt){

        this.context = ct;
        this.data = dt;
        this.dataFiltered = dt;

    }
    /*public RecyclerViewAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_crypto, parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.crypto_Name.setText(dataFiltered.get(position).getName());
        holder.crypto_Ticker.setText(dataFiltered.get(position).getTicker());
        holder.crypto_Price.setText(dataFiltered.get(position).getPrice());
        holder.crypto_Image.setImageResource(dataFiltered.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return dataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    dataFiltered = data;
                } else {
                    List<Crypto> cryptoFiltered = new ArrayList<>();
                    for (Crypto row : data) {
                        if (row.getName().toLowerCase().contains(Key.toLowerCase()) || row.getTicker().toLowerCase().contains(Key.toLowerCase())) {
                            cryptoFiltered.add(row);
                        }
                    }

                    dataFiltered = cryptoFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values=dataFiltered;
                return filterResults;
            }



            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataFiltered = (List<Crypto>) results.values;
                notifyDataSetChanged();


            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView crypto_Name;
        private TextView crypto_Price;
        private TextView crypto_Ticker;
        private ImageView crypto_Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            crypto_Name = (TextView) itemView.findViewById(R.id.crypto_name);
            crypto_Ticker = (TextView) itemView.findViewById(R.id.crypto_ticker);
            crypto_Price = (TextView) itemView.findViewById(R.id.crypto_price);
            crypto_Image = (ImageView) itemView.findViewById(R.id.crypto_image);
        }
    }
}
