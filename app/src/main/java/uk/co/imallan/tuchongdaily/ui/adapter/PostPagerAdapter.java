package uk.co.imallan.tuchongdaily.ui.adapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import uk.co.imallan.tuchongdaily.db.Table;
import uk.co.imallan.tuchongdaily.ui.fragment.PostFragment;

/**
 * Created by allan on 15/2/25.
 */
public class PostPagerAdapter extends AbstractPagerAdapter {

	private Cursor mCursor = null;

	public PostPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public Cursor swapCursor(Cursor cursor) {
		Cursor oldCursor = mCursor;
		mCursor = cursor;
		notifyDataSetChanged();
		return oldCursor;
	}

	@Override
	public Fragment getItem(int position) {
		if (mCursor == null) {
			return null;
		}
		PostFragment fragment = null;
		if (mCursor.moveToPosition(position)) {
			fragment = new PostFragment();
			Bundle args = new Bundle();
			args.putString(PostFragment.POST_SERVER_ID, mCursor.getString(mCursor.getColumnIndex(Table.Post.COLUMN_ID)));
			fragment.setArguments(args);
		}
		return fragment;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return mCursor == null ? 0 : mCursor.getCount();
	}
}
