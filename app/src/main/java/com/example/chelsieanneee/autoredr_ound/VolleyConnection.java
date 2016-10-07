package com.example.chelsieanneee.autoredr_ound;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by steph on 10/6/2016.
 */

public class VolleyConnection {
    private static VolleyConnection ourInstance;
    private RequestQueue mRequestQueue;
    public ImageLoader imageLoader;
    public static Context mContext;

    private VolleyConnection(Context context){
        mContext=context;
        mRequestQueue = getRequestQueue();
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
    }

    public  static  synchronized VolleyConnection getInstance(Context context){
        if (ourInstance == null) {
            ourInstance = new VolleyConnection(context);
        }
        return ourInstance;
    }
    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequests(Object tag){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
