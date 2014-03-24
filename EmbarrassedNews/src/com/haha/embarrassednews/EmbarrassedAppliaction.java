package com.haha.embarrassednews;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.haha.embarrassednews.activity.MainActivity;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

public class EmbarrassedAppliaction extends Application{
	private static final String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Embarrassed/log/";
	private static final String LOG_NAME = "Embarrassed_"+getCurrentDateString() + ".txt";

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate() {
		super.onCreate();
		Thread.setDefaultUncaughtExceptionHandler(handler);
	}

	UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			writeErrorLog(ex);
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	};

	/**
	 * 打印错误日志
	 * 
	 * @param ex
	 */
	protected void writeErrorLog(Throwable ex) {
		String info = null;
		ByteArrayOutputStream baos = null;
		PrintStream printStream = null;
		try {
			baos = new ByteArrayOutputStream();
			printStream = new PrintStream(baos);
			ex.printStackTrace(printStream);
			byte[] data = baos.toByteArray();
			info = new String(data);
			data = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (printStream != null) {
					printStream.close();
				}
				if (baos != null) {
					baos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Log.d("example", "崩溃信息\n" + info);
		File dir = new File(LOG_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir, LOG_NAME);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
			fileOutputStream.write(info.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	private static String getCurrentDateString() {
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date nowDate = new Date();
		result = sdf.format(nowDate);
		return result;
	}
}
