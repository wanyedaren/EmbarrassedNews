package com.haha.embarrassednews.adapter;

import java.util.ArrayList;

import com.android.volley.toolbox.NetworkImageView;
import com.haha.embarrassednews.R;
import com.haha.embarrassednews.model.ModelNew;
import com.haha.embarrassednews.utils.ImageUtil;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter{

	private ArrayList<ModelNew> mModelNews = new ArrayList<ModelNew>();
	private Context mContext;
	
	public NewsAdapter(Context context, ArrayList<ModelNew> modelNews){
		mContext = context;
		mModelNews = modelNews;
	}
	
	@Override
	public int getCount() {
		return mModelNews.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mModelNews.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2) {
		Holder holder = null;
		if (contentView==null) {
			holder = new Holder();
			contentView = View.inflate(mContext, R.layout.item_news, null);
			holder.iv_new = (NetworkImageView) contentView.findViewById(R.id.iv_news);
			holder.tv_intro = (TextView) contentView.findViewById(R.id.tv_intro);
			holder.tv_title = (TextView) contentView.findViewById(R.id.tv_title);
			contentView.setTag(holder);
		}else {
			holder = (Holder) contentView.getTag();
		}
		
		final ModelNew modelNew = mModelNews.get(arg0);
		holder.tv_intro.setText(modelNew.intro);
		holder.tv_title.setText(modelNew.title);
		ImageUtil.showImageByNetworkImageView(mContext, holder.iv_new, modelNew.pic);
		
		return contentView;
	}

	static class Holder{
		NetworkImageView iv_new;
		TextView tv_title, tv_intro;
	}
	
}
