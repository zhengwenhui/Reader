package com.zhengwenhui.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class PagePagerAdapter extends PagerAdapter {

	private int mCount;
	private int mViewSize;
	//界面列表
	private List<View> mViews;
	private String mFilePath;

	private int CAPACITY = 8000;
	private char[] mBuffer = new char[CAPACITY];
	private BufferedReader mBufferedReader;
	//private Charset mCharset;
	private int mPosition = 0;
	private ReadView mReadView;
	private ReadView mErrorReadView;
	private int Max = 1000;
	private int mCurPosition = 0;

	public PagePagerAdapter ( List<View> views, String filePath) {
		this.mViews = views;
		mViewSize = views.size();
		mFilePath = filePath;
		mCount = Integer.MAX_VALUE;
		loadBook();

		/*for (View view : mViews) {
			mReadView = (ReadView)view.getTag();
			mReadView.setText(text);
		}*/
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
		( ( ViewPager ) container).addView( view );
		mReadView = (ReadView)view.getTag();
		if( 0 == position ){
			mReadView.setText("Start");
		}
		else if(mCurPosition > position ){
			previewPage(mReadView);
		}
		
		else if(mCurPosition < position){
			nextPage(mReadView);
		}
		Log.w("position", ">>> : "+position);
		mCurPosition = position;
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

	private void loadBook() {
		try {
			String charsetName = detect(mFilePath);
			FileInputStream fileInputStream=new FileInputStream(mFilePath);
			mBufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,charsetName));
			mBufferedReader.read(mBuffer);
			/*mBufferedReader.close();*/
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检测当前文件的编码方式
	 */
	public static String detect(String filePath) {
		return "gb2312";
	}

	/**
	 * 从指定位置开始载入一页
	 */
	private void loadPage(int position) {
		String str = new String(mBuffer, position, Max);
		mReadView.setText(str);
	}

	/**
	 * 上一页按钮
	 */
	public void previewPage(View view) {

	}

	/**
	 * 下一页按钮
	 */
	public void nextPage(ReadView readView) {
		Log.i("mPosition", ":-----------------------------------------:");
		if(null != mErrorReadView){
			try{
				int harNum = mErrorReadView.getCharNum();
				Log.i("harNum:", "           "+harNum);
				mErrorReadView = null;
				mPosition += harNum;
			}catch(Exception e){
				Log.i("harNum Exception", "            -error-"+e.getMessage());
			}
		}

		try{
			Log.e("mPosition", ":"+mPosition);
			loadPage(mPosition);
			int charNum = readView.getCharNum();
			Log.e("charNum", ":"+charNum);
			mPosition += charNum;
			Log.e("mPosition", ":"+mPosition);
		}catch (Exception e) {
			// TODO: handle exception
			mErrorReadView = readView;
			Log.e("zhengwenhui", "-error-");
		}

		Log.i("mPosition", "----");
		if(( mPosition + Max )>=CAPACITY){
			for(int i = mPosition,j = 0; i < CAPACITY ; i++,j++){
				mBuffer[j] = mBuffer[i];
			}
			Log.e("CAPACITY", ":"+CAPACITY);
			Log.e("mPosition", ":"+mPosition);
			try {
				mBufferedReader.read(mBuffer, CAPACITY - mPosition, mPosition);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mPosition = 0;
		}
	}
}
