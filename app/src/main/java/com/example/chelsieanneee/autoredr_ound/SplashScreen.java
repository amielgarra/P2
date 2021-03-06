package com.example.chelsieanneee.autoredr_ound;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by chelsieanneee on 2 Aug 2016.
 */
public class SplashScreen extends Activity {

    GifImageView samp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        samp = (GifImageView) findViewById(R.id.sample);
        GifDrawable gifFromResource = null;
        try{gifFromResource = new GifDrawable( getResources(), R.drawable.drum );}catch(IOException e){}
        samp.setImageDrawable(gifFromResource);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class );
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }

}
