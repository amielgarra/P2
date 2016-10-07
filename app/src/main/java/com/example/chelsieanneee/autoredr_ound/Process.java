package com.example.chelsieanneee.autoredr_ound;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64OutputStream;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chelsieanneee on 15 Sep 2016.
 */
public class Process extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    VolleyConnection helper;
    //// TODO: 10/6/2016 set url here. Current ip address is homer's laptop
    final String API_URL = "192.168.1.18/autoredround/api/SoundProcess/ProcessAutoRedround";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_screen);
        textView = (TextView) findViewById(R.id.tvBase);
        textView1 = (TextView) findViewById(R.id.tvSongTA);

        Intent intent = getIntent();
        //textView.setText(intent.getStringExtra("Base64"));
        helper = VolleyConnection.getInstance(getBaseContext());

        String filepath = intent.getStringExtra("Filepath");
        String[] songInfo = intent.getStringArrayExtra("Info");

        String res = "";
        final File file = new File(filepath);

        InputStream inputStream = null;
        try{inputStream = new FileInputStream(file.getAbsolutePath());}catch (IOException a){Toast.makeText(getBaseContext(), a.toString(),Toast.LENGTH_SHORT).show();}
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Base64OutputStream output64 = new Base64OutputStream(output, android.util.Base64.DEFAULT);
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1 ) {
                output64.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            res=output.toString();
        }
        try{output64.close();}catch (IOException d){Toast.makeText(getBaseContext(),d.toString(),Toast.LENGTH_SHORT).show();}
        //res = output.toString();
        textView.setText(res);

        String songTitle = songInfo[0];
        String songArtist = songInfo[1];
        String songBase64 = textView.getText().toString(); //.getStringExtra("Base64");
        JSONObject obj = new JSONObject();
        try {
            obj.put("SongTitle", songTitle);
            obj.put("SongArtist", songArtist);
            obj.put("Base64", songBase64);

            textView.setText(obj.get("Base64").toString());
            textView1.setText(obj.get("SongTitle").toString() + " " + obj.get("SongArtist").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        //Sends data to server
        sendData(obj);
        /**
         StringWriter out = new StringWriter();
         try{obj.writeJSONString(out);}catch (Exception e){}finally {
         textView.setText(out.toString());
         //Toast.makeText(getBaseContext(), out.toString(), Toast.LENGTH_SHORT).show();
         }
         **/
    }

    private void sendData(final JSONObject obj){
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("Result");
                    Log.i("Response", "onResponse: " + object.toString());
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("Response", "onResponse: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response Error", "onErrorResponse: "+error.getMessage() );
            }
        }){
            @Override
            public byte[] getBody() {
                // TODO: 10/6/2016 Set data to be sent to server here
                return obj.toString().getBytes();
            }
        };
        helper.addToRequestQueue(request,"Send Audio");
    }

}
