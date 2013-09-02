package com.zhengwenhui.reader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;

public class ReadBookActivity extends Activity
implements OnPageChangeListener{

	private List<View> mViewsList;

	private ViewPager mViewPager;
	private PagePagerAdapter mAdapter;
	private int mCurrentIndex = 0;

	private String mFilePath;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);

		mFilePath = getIntent().getExtras().getString("FILE_PATH");
		mViewsList = new ArrayList<View>();
		//getBookList();

		LayoutInflater layoutInflater = getLayoutInflater();
		for (int i = 0; i < 4; i++) {
			View view = layoutInflater.inflate(R.layout.book_page, null);
			view.setTag(view.findViewById(R.id.book_page));
			mViewsList.add(view);
		}

		mViewPager = (ViewPager)findViewById(R.id.book_pages);
		mAdapter = new PagePagerAdapter(mViewsList,mFilePath);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(mCurrentIndex);
		this.setVisible(true);
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

	/*// 重新回到看书界面
	@Override
	public void onResume() {
		super.onResume();
		Layout l = tvMain.getLayout();
		if (null != l) {
			// 回到上次观看的位置
			if (hasBookMark) {
				hasBookMark = false;
				position = markPos;
			} else {
				int line = l.getLineForOffset(position);
				float sy = l.getLineBottom(line);
				tvMain.scrollTo(0, (int) sy);
			}
			Log.e("REALPOS_RESUME", "REAL_POS " + position);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.viewtxt_pre_button:
			if (tvMain.getScrollY() <= tvMain.getHeight())
				tvMain.scrollTo(0, 0);
			else
				tvMain.scrollTo(0, tvMain.getScrollY() - tvMain.getHeight());
			break;
		case R.id.viewtxt_next_button:
			if (tvMain.getScrollY() >= tvMain.getLineCount() * tvMain.getLineHeight() - tvMain.getHeight()*2)
				tvMain.scrollTo(0, tvMain.getLineCount() * tvMain.getLineHeight() - tvMain.getHeight());
			else
				tvMain.scrollTo(0, tvMain.getScrollY() + tvMain.getHeight());
			break;
		case R.id.viewtxt_main_view:

			break;
		default:
			break;
		}
	}

	// 主菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_BOOKMARK, 0, "书签");
		menu.add(0, MENU_SEARCH, 0, "搜索");

		return true;
	}

	// 主菜单点击事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_BOOKMARK:
			// 去往书签管理Activity
			Intent i = new Intent(this, BookMarkActivity.class);
			Bundle b = new Bundle();
			b.putString("BOOKNAME", strPath);
			Layout l = tvMain.getLayout();
			int line = l.getLineForVertical(tvMain.getScrollY());
			int off = l.getOffsetForHorizontal(line, 0);
			position = off;
			b.putInt("POSITION", position);
			Log.e("REALPOS_BEFORE_GO", "REAL_POS " + position);
			i.putExtras(b);
			startActivityForResult(i, REQUST_CODE_GOTO_BOOKMARK);
			break;

		case MENU_SEARCH:
			if (isInSearching) {
				tvMain.setText(strTxt);
				isInSearching = false;
			} else {
				searchDlg.setDisplay();
			}
			break;
		default:
			break;
		}
		return false;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case RESULT_OK:
			if (requestCode == REQUST_CODE_GOTO_BOOKMARK) {
				Bundle b = data.getExtras();
				int mark = b.getInt("POSITION");  
				Layout l = tvMain.getLayout();
				if (null != l) {
					// 去往书签位置
					int line = l.getLineForOffset(mark);
					float sy = l.getLineBottom(line);
					tvMain.scrollTo(0, (int) sy);
					markPos = mark;
					hasBookMark = true;
					Log.e("REALPOS_RES", "REAL_POS " + mark);
				}
			}
			break;
		}
	}*/
}