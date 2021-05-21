package com.example.stockcryptoalerter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CryptoRecyclerViewAdapter extends RecyclerView.Adapter<CryptoRecyclerViewAdapter.MyViewHolder> {

    private List<Crypto> data;
    private List<Crypto> coinList = new ArrayList<>();
    private List<Crypto> coinListCopy;
    private Context context;
    private ItemClickListener clickListener;

    public CryptoRecyclerViewAdapter(Context ct, List<Crypto> dt, ItemClickListener cl){

        this.context = ct;
        this.data = dt;
        this.coinListCopy = dt;
        this.clickListener = cl;
    }

    @NonNull
    @Override
    public CryptoRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_crypto, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        //holder.crypto_Name.setText(data.get(position).getName());
        holder.crypto_Name.setText(coinList.get(position).getName());
        //String url = data.get(position).getImageURL();
        String url = coinList.get(position).getImageURL();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.alarm)
                .into(holder.crypto_Image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickListener.onItemClick(data.get(position));
                clickListener.onItemClick(coinList.get(position));
            }
        });

    }

    @Override
    //public int getItemCount() { return data.size(); }
    public int getItemCount() {return coinList.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView crypto_Name;
        private ImageView crypto_Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            crypto_Name = (TextView) itemView.findViewById(R.id.crypto_full_name);
            crypto_Image = (ImageView) itemView.findViewById(R.id.crypto_image);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(Crypto c);
    }

    public void filterText(String text) {
        coinList.clear();
        if (text.isEmpty()){
            coinList.addAll(coinListCopy);
        }
        else {
            text = text.toLowerCase();
            for(Crypto coin: coinListCopy){
                if(coin.getName().toLowerCase().contains(text) || coin.getTicker().toLowerCase().contains(text)){
                    coinList.add(coin);
                }
            }
        }


        Collections.sort(coinList, new Comparator<Crypto>() {
            @Override
            public int compare(Crypto o1, Crypto o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        notifyDataSetChanged();
    }
}
