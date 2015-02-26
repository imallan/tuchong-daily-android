package uk.co.imallan.tuchongdaily.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.provider.PostProvider;
import uk.co.imallan.tuchongdaily.service.PostsService;
import uk.co.imallan.tuchongdaily.service.ServiceReceiver;
import uk.co.imallan.tuchongdaily.ui.adapter.PostPagerAdapter;


public class MainActivity extends AbstractActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int LOADER_POSTS = 1;

	private ViewPager mPager;

	private PostPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initElements();
		PostsService.requestPosts(this, serviceReceiver, 12, 0);
	}

	private void initElements() {
		mPager = (ViewPager) findViewById(R.id.pager_main);
		mAdapter = new PostPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mAdapter);
	}

	@Override
	boolean prepareServiceReceiver() {
		return true;
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData, Intent originalIntent, Bundle originalBundle) {
		switch (resultCode) {
			case ServiceReceiver.STATUS_DATA:
//				DataPosts dataPosts = (DataPosts) resultData.getSerializable(ServiceReceiver.EXTRA_DATA);
//				for (Post post : dataPosts.getPosts()) {
//					Log.v("POST", post.getTitle());
//				}
				break;
			case ServiceReceiver.STATUS_FINISHED:
				getSupportLoaderManager().restartLoader(LOADER_POSTS, null, this);
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


}
