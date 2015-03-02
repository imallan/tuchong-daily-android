package uk.co.imallan.tuchongdaily.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import uk.co.imallan.tuchongdaily.ui.fragment.ImageFragment;

/**
 * Created by allan on 15/3/1.
 */
public class PostImagePagerAdapter extends AbstractPagerAdapter {

	private final ArrayList<String> mIds;

	public PostImagePagerAdapter(FragmentManager fm, ArrayList<String> ids) {
		super(fm);
		this.mIds = ids;
	}

	@Override
	public Fragment getItem(int position) {
		ImageFragment imageFragment = new ImageFragment();
		Bundle args = new Bundle();
		args.putString(ImageFragment.IMAGE_SERVER_ID, mIds.get(position));
		imageFragment.setArguments(args);
		return imageFragment;
	}

	@Override
	public int getCount() {
		return mIds.size();
	}
}
