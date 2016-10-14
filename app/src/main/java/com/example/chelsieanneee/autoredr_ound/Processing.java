package com.example.chelsieanneee.autoredr_ound;

/**
 * Created by amiel on 10/12/2016.
 */

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import processing.android.PFragment;
import processing.core.PApplet;


public class Processing extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        setContentView(R.layout.process_screen);
        super.onCreate(savedInstanceState);


        FragmentManager fragmentManager = getFragmentManager();


        //Sketch sketch = sketch.setFilepath(this.getIntent().getStringExtra("Filepath").toString());
        String uri = this.getIntent().getStringExtra("Filepath");
        //Sketch a = new Sketch(uri, this);

        //a.setup();

        // a.setFilepath();


        Sketch s = new Sketch(uri);//(uri, this);
//        s.setFilepath(uri);
        PApplet sketch = s;
        PFragment fragment = new PFragment();
        fragment.setSketch(sketch);
        fragmentManager.beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }


}
