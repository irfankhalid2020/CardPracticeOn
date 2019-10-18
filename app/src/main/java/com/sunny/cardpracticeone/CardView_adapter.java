package com.sunny.cardpracticeone;

import android.app.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class CardView_adapter extends RecyclerView.Adapter<CardView_adapter.ViewHolder>{

    Activity activity;
    List<Poem> items;
    DatabaseHandler handler;


    public CardView_adapter(Activity activity, List<Poem> items){
        this.activity = activity;
        this.items = items;
        handler=new DatabaseHandler(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.itemmodel_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imageView.setImageResource(items.get(position).getPoemThumb());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //int image= items.get(holder.getAdapterPosition()).getImage();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(v.getContext(),Main2Activity.class);
                intent.putExtra("poem",items.get(position).getPoemId());
                activity.startActivity(intent);


            }
        });
        holder.sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                share.putExtra(Intent.EXTRA_SUBJECT, "write your subject");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" );
                activity.startActivity(Intent.createChooser(share, "Share App!"));


            }
        });
        holder.likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!items.get(position).isFavourite()){
                items.get(position).setFavourite(true);
                handler.setFavourite(items.get(position).getPoemId(),items.get(position).isFavourite());
                holder.likebutton.setImageResource(R.drawable.ic_favorite_black_24dp);
                }else{
                    items.get(position).setFavourite(false);
                    handler.setFavourite(items.get(position).getPoemId(),items.get(position).isFavourite());
                    holder.likebutton.setImageResource(R.drawable.play);
                }
                notifyDataSetChanged();
            }
        });

        if(items.get(position).isFavourite())
            holder.likebutton.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            holder.likebutton.setImageResource(R.drawable.play);

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        CardView cardView;
        ImageButton sharebutton,likebutton;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView= (ImageView)itemView.findViewById(R.id.listImageView);
            cardView= itemView.findViewById(R.id.cardview);
            sharebutton= itemView.findViewById(R.id.sharebutton);
            likebutton=itemView.findViewById(R.id.likebutton);



        }
    }
}

