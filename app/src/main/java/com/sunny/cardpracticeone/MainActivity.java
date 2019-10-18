package com.sunny.cardpracticeone;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView CardRecycler;
    CardView_adapter cardView_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardRecycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        CardRecycler.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        CardRecycler.setLayoutManager(linearLayoutManager);
        cardView_adapter = new CardView_adapter(this, getPoems());

        CardRecycler.setAdapter(cardView_adapter);
    }

    public ArrayList<Poem> getPoems() {
        ArrayList<Poem> poemsData = new ArrayList<>();
//        for (int i = 0; i < PoemConstants.POEMS_SOUNDS.length; i++) {
//            Poem poem = new Poem(PoemConstants.POEMS_SOUNDS[i], PoemConstants.POEM_THUMBS[i]);
//            poem.setFavourite(false);
//            poemsData.add(poem);
//        }

        DatabaseHandler handler=new DatabaseHandler(this);
        poemsData.addAll(getIntent().getBooleanExtra("fav",false)?handler.getFavouriteRingtones():handler.getAllRingtones());
        handler.close();
        return poemsData;
    }

}
