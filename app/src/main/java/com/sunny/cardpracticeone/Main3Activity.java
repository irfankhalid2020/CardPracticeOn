package com.sunny.cardpracticeone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        populateData();
    }

    private void populateData(){
        DatabaseHandler handler=new DatabaseHandler(this);
        if(handler.recordInserted()==0)
        for (int i = 0; i < PoemConstants.POEMS_SOUNDS.length; i++) {
            Poem poem = new Poem(PoemConstants.POEMS_SOUNDS[i], PoemConstants.POEM_THUMBS[i]);
            poem.setFavourite(false);
            handler.addData(poem);
        }
        handler.close();
    }

    private void finishActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name))
                .setMessage("Are you Sure you want to close?")
                .setIcon(R.mipmap.ic_launcher);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishActivity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void clickBtn(View view) {
        int id = view.getId();
        if (id ==R.id.btn1){
            Intent intent= new Intent(Main3Activity.this,MainActivity.class);
            startActivity(intent);
        }else if (id ==R.id.btn2){
            Intent intent= new Intent(Main3Activity.this,MainActivity.class);
            intent.putExtra("fav",true);
            startActivity(intent);
        }
    }
}
