package com.sunny.cardpracticeone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView CardRecycler;
    CardView_adapter cardView_adapter;
    List<Item_Pojo> textsimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardRecycler = (RecyclerView)findViewById(R.id.my_recycler_view);
        List();
        CardRecycler.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        CardRecycler.setLayoutManager(linearLayoutManager);
        cardView_adapter = new CardView_adapter(this, textsimple);

        CardRecycler.setAdapter(cardView_adapter);
    }
    public void List(){


        textsimple = new ArrayList<>();
        textsimple.add(new Item_Pojo(R.drawable.image1));
        textsimple.add(new Item_Pojo(R.drawable.image2));
        textsimple.add(new Item_Pojo(R.drawable.image3));
        textsimple.add(new Item_Pojo(R.drawable.image4));
        textsimple.add(new Item_Pojo(R.drawable.image5));
        textsimple.add(new Item_Pojo(R.drawable.image6));
        textsimple.add(new Item_Pojo(R.drawable.image7));
        textsimple.add(new Item_Pojo(R.drawable.image8));
        textsimple.add(new Item_Pojo(R.drawable.image9));
        textsimple.add(new Item_Pojo(R.drawable.image10));
        textsimple.add(new Item_Pojo(R.drawable.image11));
        textsimple.add(new Item_Pojo(R.drawable.image12));
        textsimple.add(new Item_Pojo(R.drawable.image13));
        textsimple.add(new Item_Pojo(R.drawable.image14));
        textsimple.add(new Item_Pojo(R.drawable.image15));

    }

}
