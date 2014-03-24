package com.haha.embarrassednews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class ImageUtil {
	/**
	 * ¿˚”√NetworkImageViewœ‘ æÕ¯¬ÁÕº∆¨
	 */
	public static void showImageByNetworkImageView(Context context, NetworkImageView mNetworkImageView, String url) {
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
		ImageCache imageCache = new ImageCache() {
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		mNetworkImageView.setTag(url);
		mNetworkImageView.setImageUrl(url, imageLoader);
	}
}
