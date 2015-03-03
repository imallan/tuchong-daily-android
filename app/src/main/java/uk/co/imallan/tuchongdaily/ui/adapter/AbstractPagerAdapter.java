package uk.co.imallan.tuchongdaily.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by allan on 15/2/25.
 */
public abstract class AbstractPagerAdapter extends PagerAdapter {

	private final SparseArray<Fragment> registeredFragments;

	private final FragmentManager mFragmentManager;

	FragmentTransaction mCurrentTransaction = null;

	public AbstractPagerAdapter(FragmentManager fm) {
		this.mFragmentManager = fm;
		registeredFragments = new SparseArray<Fragment>();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (mCurrentTransaction == null) {
			mCurrentTransaction = mFragmentManager.beginTransaction();
		}
		Fragment fragment = getItem(position);
		registeredFragments.put(position, fragment);
		mCurrentTransaction.add(container.getId(), fragment);
		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if (mCurrentTransaction == null) {
			mCurrentTransaction = mFragmentManager.beginTransaction();
		}
		Fragment registeredFragment = getRegisteredFragment(position);
		mCurrentTransaction.remove(registeredFragment);
		registeredFragments.remove(position);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return ((Fragment) object).getView() == view;
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		if (mCurrentTransaction != null) {
			mCurrentTransaction.commitAllowingStateLoss();
			mCurrentTransaction = null;
			mFragmentManager.executePendingTransactions();
		}
	}

	public abstract Fragment getItem(int position);

	public Fragment getRegisteredFragment(int position) {
		return registeredFragments.get(position);
	}
}
