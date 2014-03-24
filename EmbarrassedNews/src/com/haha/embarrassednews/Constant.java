package com.haha.embarrassednews;

import android.util.Log;

public class Constant {
	
	private static boolean debug = true;
	
	public static final String HOST = "";
	public static final String URL_Get_NEWS = "";
	
	public static void LogI(String tag, String value){
		if (debug) {
			Log.i(tag, value);
		}
	}
	
	public static void LogE(String tag, String value){
		if (debug) {
			Log.e(tag, value);
		}
	}
}
