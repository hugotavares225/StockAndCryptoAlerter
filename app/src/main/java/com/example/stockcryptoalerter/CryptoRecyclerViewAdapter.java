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


import java.util.List;

public class CryptoRecyclerViewAdapter extends RecyclerView.Adapter<CryptoRecyclerViewAdapter.MyViewHolder> {

    private List<Crypto> data;
    private Context context;
    private ItemClickListener clickListener;

    public CryptoRecyclerViewAdapter(Context ct, List<Crypto> dt, ItemClickListener cl){

        this.context = ct;
        this.data = dt;
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

        holder.crypto_Name.setText(data.get(position).getName());
        String url = data.get(position).getImageURL();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.alarm)
                .into(holder.crypto_Image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(data.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

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
    
}
