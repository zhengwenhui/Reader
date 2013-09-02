package com.zhengwenhui.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class BookShelfActivity extends Activity 
implements OnPageChangeListener{

	private ViewPager mViewPager;
	private BooksPagerAdapter mAdapter;
	private List<View> mViewsList;
	private List<File> bookList;
	private int mCurrentIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_shelf);
		mViewsList = new ArrayList<View>();

		getBookList();

		if(bookList != null && bookList.size()> 0 ){
			LayoutInflater layoutInflater = getLayoutInflater();

			for (int i = 0; i < 6; i++) {
				View view = layoutInflater.inflate(R.layout.book_cover, null);
				view.setTag(view.findViewById(R.id.book_cover));
				mViewsList.add( view );
			}

			mViewPager = (ViewPager)findViewById(R.id.book_cover_list);
			mAdapter = new BooksPagerAdapter(mViewsList,bookList);
			mViewPager.setAdapter(mAdapter);
			mViewPager.setOnPageChangeListener(this);
			//mViewPager.setOnTouchListener(this);
		}
	}

	private List<File> getBookList(){
		bookList = new ArrayList<File>();
		if (Environment.getExternalStorageState().equals(  
				Environment.MEDIA_MOUNTED)) {  
			File path = Environment.getExternalStorageDirectory(); 
			getFileName(path.listFiles());
		}
		return bookList;
	}

	private void getFileName(File[] files) {
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					getFileName(file.listFiles());
					Log.i("zhengwenhui",file.getPath().toString());
				} else {
					String fileName = file.getName();
					if (fileName.endsWith(".txt")) {
						Log.v("zhengwenhui", "file.Name:  " + file.getName()); 
						bookList.add(file);
					}
				}
			}
		}  
	}

	public void onClick( View view ){
		File file = bookList.get(mCurrentIndex);
		Log.v("zhengwenhui", file.getPath());
		Intent intent = new Intent(this,ReadBookActivity.class);
		intent.putExtra("FILE_PATH", file.getPath());
		startActivity(intent);
	}

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		mCurrentIndex = arg0;
	}
}