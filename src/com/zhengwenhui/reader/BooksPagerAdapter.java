package com.zhengwenhui.reader;

import java.io.File;
import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

public class BooksPagerAdapter extends PagerAdapter {

	private int mCount;
	private int mViewSize;
	//界面列表
	private List<View> mViews;
	private List<File> mList;

	public BooksPagerAdapter ( List<View> views, List<File> list) {
		this.mViews = views;
		mViewSize = views.size();
		mCount = list.size();
		mList = list;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		View view = mViews.get( position % mViewSize);
		( ( ViewPager ) container).removeView( view );
	}

	@Override
	public void finishUpdate(View container) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		View view = mViews.get(position % mViewSize);
		File file = mList.get(position);
		( ( ViewPager ) container).addView( view );
		((Button)view.getTag()).setText(file.getName());
		//mOnInstantiateItemListener.OnInstantiateItem(position,position);
		return view;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		// TODO Auto-generated method stub
		super.restoreState(state, loader);
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return super.saveState();
	}

	@Override
	public void startUpdate(View container) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCount;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
}
