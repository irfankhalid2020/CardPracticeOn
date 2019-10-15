package com.sunny.cardpracticeone;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class CardView_adapter extends RecyclerView.Adapter<CardView_adapter.ViewHolder>{

    Activity activity;
    List<Item_Pojo> items;

    public CardView_adapter(Activity activity, List<Item_Pojo> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.itemmodel_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imageView.setImageResource(items.get(position).getImage());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //int image= items.get(holder.getAdapterPosition()).getImage();
                Bundle bundle = new Bundle();

                Intent intent = new Intent(v.getContext(),Main2Activity.class);
                intent.putExtra("poem",position);
                activity.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView= (ImageView)itemView.findViewById(R.id.listImageView);
            cardView= itemView.findViewById(R.id.cardview);
        }
    }
}

