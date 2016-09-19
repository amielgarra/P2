package com.example.chelsieanneee.autoredr_ound;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by chelsieanneee on 2 Aug 2016.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        /**ImageView imageView1 = (ImageView)findViewById(R.id.ViewLogo);
        imageView1.setBackgroundResource(R.drawable.logoanimation);
        anim = (AnimationDrawable) imageView1.getBackground();
        anim.start();**/
        //Intent intent = new Intent(SplashScreen.this,MainActivity.class );
        //startActivity(intent);

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
