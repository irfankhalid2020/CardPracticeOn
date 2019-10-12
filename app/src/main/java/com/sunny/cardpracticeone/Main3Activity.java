package com.sunny.cardpracticeone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    }

    public void clickBtn(View view) {
        int id = view.getId();
        if (id ==R.id.btn1){
            Intent intent= new Intent(Main3Activity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
