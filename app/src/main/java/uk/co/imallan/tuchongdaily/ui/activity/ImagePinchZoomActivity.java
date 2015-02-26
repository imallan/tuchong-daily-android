package uk.co.imallan.tuchongdaily.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import uk.co.imallan.tuchongdaily.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImagePinchZoomActivity extends AbstractActivity {

	public static final String EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL";

	private static final String EXTRA_THUMBNAIL_URL = "EXTRA_THUMBNAIL_URL";

	PhotoViewAttacher mAttacher;

	private ImageView mImageView;

	private static final String LOG_TAG = ImagePinchZoomActivity.class.getSimpleName();

	public static void startActivity(Context context, String imageURL, View sharedView, String thumbnailURL) {
		Intent intent = new Intent(context, ImagePinchZoomActivity.class);
		intent.putExtra(EXTRA_IMAGE_URL, imageURL);
		intent.putExtra(EXTRA_THUMBNAIL_URL, thumbnailURL);
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_pinch_zoom);
		mImageView = (ImageView) findViewById(R.id.image_pinch_zoom);
		mAttacher = new PhotoViewAttacher(mImageView);
		String thumbnail = getIntent().getStringExtra(EXTRA_THUMBNAIL_URL);
		if (!TextUtils.isEmpty(thumbnail)) {
			requestThumbnailImage(thumbnail);
		} else {
			requestFullImage();
		}
	}

	private void requestThumbnailImage(String thumbnail) {
		Picasso.with(this).load(thumbnail).noPlaceholder().noFade().into(getTarget(new Callback() {
			@Override
			public void onFinished() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						requestFullImage();
					}
				}, 1000);
			}
		}));
	}

	private void requestFullImage() {
		Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE_URL)).noPlaceholder().noFade().into(getTarget(null));
	}

	private Target getTarget(final Callback callback) {
		return new Target() {
			@Override
			public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
				mImageView.post(new Runnable() {
					@Override
					public void run() {
						mImageView.setImageBitmap(bitmap);
						mAttacher.update();
						if (callback != null) {
							callback.onFinished();
						}
					}
				});
			}

			@Override
			public void onBitmapFailed(Drawable errorDrawable) {

			}

			@Override
			public void onPrepareLoad(Drawable placeHolderDrawable) {

			}
		};
	}

	@Override
	boolean prepareServiceReceiver() {
		return false;
	}

	interface Callback {

		public void onFinished();

	}
}
