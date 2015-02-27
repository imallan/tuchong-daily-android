package uk.co.imallan.tuchongdaily.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import uk.co.imallan.tuchongdaily.R;

/**
 * Created by allan on 15/2/27.
 */
public class ImageActivity extends AbstractActivity {

	private static final String EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL";

	private ImageView mImageView;

	public static void startActivity(Context context, String imageURL, View sharedView) {
		Intent intent = new Intent(context, ImageActivity.class);
		intent.putExtra(EXTRA_IMAGE_URL, imageURL);
		ActivityOptionsCompat options;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, sharedView, context.getString(R.string.share_hero));
		} else {
			options = ActivityOptionsCompat.makeScaleUpAnimation(sharedView, 0, 0, sharedView.getWidth(), sharedView.getHeight());
		}
		if (!TextUtils.isEmpty(imageURL)) {
			intent.putExtra(EXTRA_IMAGE_URL, imageURL);
		} else {
			throw new RuntimeException("You must provide an image url");
		}
		ActivityCompat.startActivity((Activity) context, intent, options.toBundle());
	}

	@Override
	boolean prepareServiceReceiver() {
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			postponeEnterTransition();
		}
		mImageView = (ImageView) findViewById(R.id.image_view_image);
		String imageURL = getIntent().getStringExtra(EXTRA_IMAGE_URL);
		Picasso.with(this).load(imageURL).noPlaceholder().noFade().into(mImageView, new Callback() {
			@Override
			public void onSuccess() {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					startPostponedEnterTransition();
				}
			}

			@Override
			public void onError() {

			}
		});
	}

}
