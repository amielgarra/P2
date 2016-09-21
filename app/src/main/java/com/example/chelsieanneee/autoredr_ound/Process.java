package com.example.chelsieanneee.autoredr_ound;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.io.StringWriter;

/**
 * Created by chelsieanneee on 15 Sep 2016.
 */
public class Process extends AppCompatActivity {

    TextView textView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_screen);
        textView = (TextView) findViewById(R.id.tvBase);
        textView1 = (TextView) findViewById(R.id.tvSongTA);

        Intent intent = getIntent();
        //textView.setText(intent.getStringExtra("Base64"));
        String[] songInfo = intent.getStringArrayExtra("Info");

        String songTitle = songInfo[0];
        String songArtist = songInfo[1];
        String songBase64 = intent.getStringExtra("Base64");
        JSONObject obj = new JSONObject();

        obj.put("SongTitle",songTitle);
        obj.put("SongArtist",songArtist);
        obj.put("Base64",songBase64);

        textView.setText(obj.get("Base64").toString());
        textView1.setText(obj.get("SongTitle").toString() + " " + obj.get("SongArtist").toString());

        /**
        StringWriter out = new StringWriter();
        try{obj.writeJSONString(out);}catch (Exception e){}finally {
        textView.setText(out.toString());
            //Toast.makeText(getBaseContext(), out.toString(), Toast.LENGTH_SHORT).show();
        }
         **/
    }
}
