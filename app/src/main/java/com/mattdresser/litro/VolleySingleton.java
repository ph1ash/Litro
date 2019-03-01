package com.mattdresser.litro;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

class VolleySingleton {
    private static final VolleySingleton ourInstance = new VolleySingleton();

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    static VolleySingleton getInstance() {
        return ourInstance;
    }

    private VolleySingleton() {
        this.requestQueue = Volley.newRequestQueue(LitroManager.getAppContext());
        this.imageLoader = new ImageLoader(this.requestQueue, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap>
                    cache = new LruCache<>(5);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    public ImageLoader getImageLoader()
    {
        return this.imageLoader;
    }
}
