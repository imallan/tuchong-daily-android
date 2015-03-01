package uk.co.imallan.tuchongdaily.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.provider.PostProvider;
import uk.co.imallan.tuchongdaily.service.PostsService;
import uk.co.imallan.tuchongdaily.service.ServiceReceiver;
import uk.co.imallan.tuchongdaily.ui.adapter.PostPagerAdapter;
import uk.co.imallan.tuchongdaily.ui.fragment.PostFragment;


public class MainActivity extends AbstractActivity
		implements LoaderManager.LoaderCallbacks<Cursor>, ViewPager.OnPageChangeListener {

	private static final int LOADER_POSTS = 1;

	private ViewPager mPager;

	private PostPagerAdapter mAdapter;

	private long mLastBackClicked = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initElements();
		PostsService.requestPosts(this, serviceReceiver, 12, 0);
		getSupportLoaderManager().initLoader(LOADER_POSTS, null, this);
	}

	private void initElements() {
		mPager = (ViewPager) findViewById(R.id.pager_main);
		mAdapter = new PostPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(this);
	}

	@Override
	boolean prepareServiceReceiver() {
		return true;
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData, Intent originalIntent, Bundle originalBundle) {
		switch (resultCode) {
			case ServiceReceiver.STATUS_DATA:
				break;
			case ServiceReceiver.STATUS_FINISHED:
				getSupportLoaderManager().restartLoader(LOADER_POSTS, null, this);
				break;
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		switch (id) {
			case LOADER_POSTS:
				return new CursorLoader(this, PostProvider.uriPosts(), null, null, null, null);
			default:
				return null;
		}
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		switch (loader.getId()) {
			case LOADER_POSTS:
				mAdapter.swapCursor(data);
				break;
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}


	private PostFragment getRegisteredFragment(int position) {
		return (PostFragment) mAdapter.getRegisteredFragment(position);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		PostFragment fromFragment = getRegisteredFragment(position);
		if (fromFragment != null) {
			fromFragment.onPageScrollFrom(positionOffset, positionOffsetPixels);
		}
		PostFragment toFragment = getRegisteredFragment(position + 1);
		if (toFragment != null) {
			toFragment.onPageScrollto(positionOffset, positionOffsetPixels);
		}
	}

	@Override
	public void onPageSelected(int position) {
		PostFragment fragment = getRegisteredFragment(position);
		if (fragment != null) {
			fragment.onPageSelected();
		}

	}

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - mLastBackClicked < 1000) {
			super.onBackPressed();
		} else {
			Toast.makeText(this, R.string.double_click_to_exit, Toast.LENGTH_SHORT).show();
			mLastBackClicked = System.currentTimeMillis();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}
