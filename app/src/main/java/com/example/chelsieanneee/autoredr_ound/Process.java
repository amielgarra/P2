package com.example.chelsieanneee.autoredr_ound;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by chelsieanneee on 15 Sep 2016.
 */
public class Process extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_screen);
        textView = (TextView)findViewById(R.id.tvbase);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("Base64"));
    }
}
