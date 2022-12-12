package com.bfcai.meals;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList ids,titles, prices , names , phones , addresses;

    CustomAdapter(Activity activity, Context context, ArrayList ids, ArrayList titles, ArrayList prices ,ArrayList names ,ArrayList phones ,ArrayList addresses  ){
        this.activity = activity;
        this.context = context;
        this.ids = ids;
        this.titles = titles;
        this.prices = prices;
        this.names = names;
        this.phones = phones;
        this.addresses = addresses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_wasfa_layout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id_txt.setText(String.valueOf(ids.get(holder.getAdapterPosition())));
        holder.title_txt.setText(String.valueOf(titles.get(holder.getAdapterPosition())));
        holder.price_txt.setText(String.valueOf(prices.get(holder.getAdapterPosition())));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updateWasafaty.class);
                intent.putExtra("id", String.valueOf(ids.get(holder.getAdapterPosition())));
                intent.putExtra("title", String.valueOf(titles.get(holder.getAdapterPosition())));
                intent.putExtra("price", String.valueOf(prices.get(holder.getAdapterPosition())));
                intent.putExtra("name", String.valueOf(names.get(holder.getAdapterPosition())));
                intent.putExtra("phone", String.valueOf(phones.get(holder.getAdapterPosition())));
                intent.putExtra("address", String.valueOf(addresses.get(holder.getAdapterPosition())));

                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, title_txt, price_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            title_txt = itemView.findViewById(R.id.title_txt);
            price_txt = itemView.findViewById(R.id.price_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}