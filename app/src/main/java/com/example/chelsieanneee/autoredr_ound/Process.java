package com.example.chelsieanneee.autoredr_ound;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Process extends AppCompatActivity {
    boolean isProcessing = false;
    TextView textView;
    TextView textView1;
    VolleyConnection helper;
    ProgressDialog progressDialog ;
    String audioBase64;
    //// TODO: 10/6/2016 set url here. Current ip address is homer's laptop
    final String API_URL = "http://192.168.8.99/autoredround/api/SoundProcess/ProcessAutoRedround";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_screen);
        textView = (TextView) findViewById(R.id.tvBase);
        textView1 = (TextView) findViewById(R.id.tvSongTA);

        Intent intent = getIntent();
        //textView.setText(intent.getStringExtra("Base64"));
        helper = VolleyConnection.getInstance(getBaseContext());
        String[] songInfo = intent.getStringArrayExtra("Info");

        String songTitle = songInfo[0];
        String songArtist = songInfo[1];
        textView.setText(audioBase64);
        textView1.setText(songTitle + " " + songArtist);
        if (isProcessing!=true) {
            progressDialog = new ProgressDialog(this);
            String filepath = intent.getStringExtra("Filepath");

            final File file = new File(filepath);
            audioBase64 = convertToBase64(file);

            String songBase64 = audioBase64.toString();

            JSONObject obj = new JSONObject();
            try {
                obj.put("SongTitle", songTitle);
                obj.put("SongArtist", songArtist);
                obj.put("Base64", songBase64);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Sends data to server
            sendData(obj);
        }
        /**
         StringWriter out = new StringWriter();
         try{obj.writeJSONString(out);}catch (Exception e){}finally {
         textView.setText(out.toString());
         //Toast.makeText(getBaseContext(), out.toString(), Toast.LENGTH_SHORT).show();
         }
         **/
    }

    private void sendData(final JSONObject obj){
        progressDialog.setMessage("Sending files to server...");
        progressDialog.show();
        isProcessing = true;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                progressDialog.hide();
                Log.i("Response", "onResponse: " + response.toString());
                isProcessing = false;
                try {
                    if (response.get("result")!=null&&response.getString("result")=="Success") {
                        String outputName = response.getString("data");
                        //Use this to access pdf file in server. send to intent to open in browser
                        String OutputURL = "http://192.168.8.99/autoredround/output/" + outputName;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("Response", "onResponse: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                isProcessing=false;
                Log.e("Response Error", "onErrorResponse: "+error.getMessage() );
            }
        }){
            @Override
            public byte[] getBody() {
                // TODO: 10/6/2016 Set data to be sent to server here
                return obj.toString().getBytes();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        helper.addToRequestQueue(request,"Send Audio");
    }

    private String convertToBase64(File file){
        try{
            byte[] audioByte;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
            byte[] buffer = new byte[8192];
            int bytesRead;
            while (-1!=(bytesRead = inputStream.read(buffer))){
                output.write(buffer,0,bytesRead);
            }
            audioByte= output.toByteArray();
            return Base64.encodeToString(audioByte,Base64.DEFAULT);
        }catch (IOException a){
            Toast.makeText(getBaseContext(), a.toString(),Toast.LENGTH_SHORT).show();
            return  null;
        }
    }

}
