package com.haha.embarrassednews.activity;

import java.util.ArrayList;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.haha.embarrassednews.Constant;
import com.haha.embarrassednews.R;
import com.haha.embarrassednews.adapter.NewsAdapter;
import com.haha.embarrassednews.model.ModelNew;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends BaseActivity implements OnRefreshListener2<ListView> {

	private PullToRefreshListView mPtrLvNew;
	private String firstNewID = "";
	private String lastNewID = "";
	private ListView mLVNews;
	private NewsAdapter mNewsAdapter;
	private ArrayList<ModelNew> mModelNews = new ArrayList<ModelNew>();
	private int mPageSize = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mPtrLvNew = (PullToRefreshListView) findViewById(R.id.lv_new);
		mPtrLvNew.setOnRefreshListener(this);
		mPtrLvNew.setMode(Mode.BOTH);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
			}
		}, 400);
		
		mLVNews = mPtrLvNew.getRefreshableView();
		mNewsAdapter = new NewsAdapter(this, mModelNews);
		mLVNews.setAdapter(mNewsAdapter);
	}
	
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		getNewsData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		
	}

	/**
	 * 利用Volley获取JSON数据
	 */
	private void getNewsData() {
		RequestQueue requestQueue = Volley.newRequestQueue(this);

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.HOST + Constant.URL_Get_NEWS, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// 处理返回的JSON数据
						Constant.LogI("getNewsData-->", response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						Constant.LogE("getNewsData-->", arg0.toString());
					}
				});
		requestQueue.add(jsonObjectRequest);
	}
}





