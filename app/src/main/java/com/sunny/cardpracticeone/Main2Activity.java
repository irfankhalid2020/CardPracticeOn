package com.sunny.cardpracticeone;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;

public class Main2Activity extends AppCompatActivity {
    int i=1;
    Button playBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;
    Uri mediaPath;
    int[] poems={R.raw.nazam1,R.raw.nazam2,R.raw.nazam3,R.raw.nazam4,R.raw.nazam6,R.raw.nazam7,R.raw.nazam8,R.raw.nazam9,R.raw.nazam10,R.raw.nazam11,R.raw.nazam12,R.raw.nazam13,R.raw.nazam14,R.raw.nazam15,R.raw.nazam16};
    String ID="R.raw.nazam1,R.raw.nazam2,R.raw.nazam3,R.raw.nazam4,R.raw.nazam6,R.raw.nazam7,R.raw.nazam8,R.raw.nazam9,R.raw.nazam10,R.raw.nazam11,R.raw.nazam12,R.raw.nazam13,R.raw.nazam14,R.raw.nazam15,R.raw.nazam16";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int poem=getIntent().getIntExtra("poem",0);



        //  Field[] fields =R.raw.class.getFields();


        playBtn = (Button) findViewById(R.id.playBtn);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimeLabel);

        // Media Player
        mp = MediaPlayer.create(this, poems[poem]);

        mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + String.valueOf(poems[i]));
        try {
            mp.setDataSource(getApplicationContext(), mediaPath);
            mp.prepare();
            mp.start();



        } catch (Exception e) {
            e.printStackTrace();
        }


        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Do something when media player end playing
                playBtn.setBackgroundResource(R.drawable.play);
                mp.seekTo(0);
            }
        });
        mp.setLooping(false);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();


        // Position Bar
        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser && mp.isPlaying()) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


        // Volume Bar


        // Thread (Update positionBar & timeLabel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            positionBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying() && mp!=null){
            mp.release();
            mp=null;
        }
    }

    public void playBtnClick(View view) {

        if (!mp.isPlaying()) {
            // Stopping

            mp.start();

            playBtn.setBackgroundResource(R.drawable.stop);

        } else {
            // Playing
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }

    }


    public void doRewind(View view) {
        if(i!=0) {
            i--;
            mp.reset();
            try {
                mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + String.valueOf(poems[i]));
                mp.setDataSource(getApplicationContext(), mediaPath);
                mp.prepare();
                mp.seekTo(0);

                totalTime = mp.getDuration();
                positionBar.setMax(totalTime);
                mp.start();
                playBtn.setBackgroundResource(R.drawable.stop);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            mp.seekTo(0);
        }
    }

    public void doFastForward(View view) {
        Log.e("length", String.valueOf(poems.length));
        if(i<poems.length-1) {
            i++;
            try {
                mp.reset();
                mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + String.valueOf(poems[i]));
                mp.setDataSource(getApplicationContext(), mediaPath);
                mp.prepare();
                mp.seekTo(0);

                totalTime = mp.getDuration();
                positionBar.setMax(totalTime);
                mp.start();
                playBtn.setBackgroundResource(R.drawable.stop);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            mp.seekTo(0);
        }

}}