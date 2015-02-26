package uk.co.imallan.tuchongdaily.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by allan on 15/2/25.
 */
public abstract class AbstractPagerAdapter extends FragmentPagerAdapter {

	private final SparseArray<Fragment> registeredFragments;

	public AbstractPagerAdapter(FragmentManager fm) {
		super(fm);
		registeredFragments = new SparseArray<Fragment>();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = (Fragment) super.instantiateItem(container, position);
		registeredFragments.put(position, fragment);
		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		registeredFragments.remove(position);
		super.destroyItem(container, position, object);
	}

	public Fragment getRegisteredFragment(int position) {
		return registeredFragments.get(position);
	}
}
