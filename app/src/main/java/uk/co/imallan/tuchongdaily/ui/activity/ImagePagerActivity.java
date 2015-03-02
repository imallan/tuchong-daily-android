package uk.co.imallan.tuchongdaily.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.ui.adapter.PostImagePagerAdapter;
import uk.co.imallan.tuchongdaily.ui.fragment.ImageFragment;

/**
 * Created by allan on 15/3/1.
 */
public class ImagePagerActivity extends AbstractActivity implements ViewPager.OnPageChangeListener {

	private static final String EXTRA_IMAGE_IDS = "EXTRA_IMAGE_IDS";

	public static final int CODE_VIEW_IMAGE_PAGER = 1;

	public static String EXTRA_IMAGE_POSITION = "EXTRA_IMAGE_POSITION";

	private ArrayList<String> mIds;

	private int mPosition;

	private ViewPager mPager;

	private PostImagePagerAdapter mAdapter;

	public static void startActivity(Activity activity, ArrayList<String> serverIds, int position, View view) {
		Intent intent = new Intent(activity, ImagePagerActivity.class);
		intent.putExtra(EXTRA_IMAGE_IDS, serverIds);
		intent.putExtra(EXTRA_IMAGE_POSITION, position);
		ActivityOptionsCompat options;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, activity.getString(R.string.share_hero));
		} else {
			options = ActivityOptionsCompat.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
		}
		ActivityCompat.startActivityForResult(activity, intent, CODE_VIEW_IMAGE_PAGER, options.toBundle());
	}

	@Override
	boolean prepareServiceReceiver() {
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_pager);
		mIds = getIntent().getStringArrayListExtra(EXTRA_IMAGE_IDS);
		mPosition = getIntent().getIntExtra(EXTRA_IMAGE_POSITION, 0);
		mPager = (ViewPager) findViewById(R.id.pager_images);
		mAdapter = new PostImagePagerAdapter(getSupportFragmentManager(), mIds);
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(mPosition);
		mPager.setOnPageChangeListener(this);
		initTransitions();
	}

	private void initTransitions() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			postponeEnterTransition();
			setEnterSharedElementCallback(new SharedElementCallback() {
				@SuppressLint("NewApi")
				@Override
				public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
					super.onMapSharedElements(names, sharedElements);
					if (mAdapter.getRegisteredFragment(mPosition) != null) {
						names.clear();
						sharedElements.clear();
						View view = ((ImageFragment) mAdapter.getRegisteredFragment(mPosition)).mImage;
						names.add(view.getTransitionName());
						sharedElements.put(view.getTransitionName(), view);
					}
				}
			});
		}

//		mPager.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//			@SuppressLint("NewApi")
//			public boolean onPreDraw() {
//				mPager.getViewTreeObserver().removeOnPreDrawListener(this);
//				startPostponedEnterTransition();
//				return true;
//			}
//		});
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		mPosition = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void finishAfterTransition() {
		Intent data = new Intent();
		data.putExtra(EXTRA_IMAGE_POSITION, mPosition);
		setResult(Activity.RESULT_OK, data);
		super.finishAfterTransition();
	}
}
