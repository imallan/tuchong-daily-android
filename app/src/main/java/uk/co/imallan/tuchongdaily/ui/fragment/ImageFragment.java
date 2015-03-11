package uk.co.imallan.tuchongdaily.ui.fragment;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.db.Table;
import uk.co.imallan.tuchongdaily.provider.ImageProvider;
import uk.co.imallan.tuchongdaily.utils.ImageUtils;

/**
 * Created by allan on 15/3/1.
 */
public class ImageFragment extends AbstractFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

	public static final String IMAGE_SERVER_ID = "IMAGE_SERVER_ID";

	private static final int LOADER_REQUEST_IMAGE = 1;

	private View mRootView;

	public ImageView mImage;

	private String mServerId;

	private TextView mCamera;

	private TextView mLens;

	@Override
	protected boolean prepareServiceReceiver() {
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		mServerId = arguments.getString(IMAGE_SERVER_ID);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_image, container, false);
		mImage = (ImageView) mRootView.findViewById(R.id.image_view_image_framgent);
		mCamera = (TextView) mRootView.findViewById(R.id.text_image_fragment_camera_info);
		mLens = (TextView) mRootView.findViewById(R.id.text_image_fragment_lens_info);
		initTransitions();
		return mRootView;
	}

	private void initTransitions() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Slide slideInBottom = new Slide();
			slideInBottom.addTarget(R.id.text_image_fragment_camera_info);
			slideInBottom.addTarget(R.id.text_image_fragment_lens_info);
			slideInBottom.setStartDelay(300);
			getActivity().getWindow().setEnterTransition(slideInBottom);
			Slide slide = new Slide();
			slide.addTarget(R.id.text_image_fragment_camera_info);
			slide.addTarget(R.id.text_image_fragment_lens_info);
		}
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mImage.setOnClickListener(this);
		mRootView.setOnClickListener(this);
		getLoaderManager().initLoader(LOADER_REQUEST_IMAGE, null, this);

	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		switch (id) {
			case LOADER_REQUEST_IMAGE:
				return new CursorLoader(getActivity(), ImageProvider.uriImage(mServerId),
						null, null, null, null);
			default:
				return null;
		}
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		switch (loader.getId()) {
			case LOADER_REQUEST_IMAGE:
				if (data.moveToFirst()) {
					Picasso.with(getActivity()).load(data.getString(data.getColumnIndex(Table.Image.COLUMN_URL_LARGE)))
							.transform(new ImageUtils.LimitImageSizeTransformation(ImageUtils.LimitImageSizeTransformation.QUALITY.QUALITY_1080P))
							.noPlaceholder().noFade().into(mImage, new Callback() {
						@Override
						public void onSuccess() {
							scheduleStartPostponedTransition(mImage);
						}

						@Override
						public void onError() {
							scheduleStartPostponedTransition(mImage);
						}
					});
					String camera = data.getString(data.getColumnIndex(Table.Image.COLUMN_CAMERA));
					String lens = data.getString(data.getColumnIndex(Table.Image.COLUMN_LENS));
					if (!TextUtils.isEmpty(camera)) {
						mCamera.setText(camera);
					}
					if (!TextUtils.isEmpty(lens)) {
						mLens.setText(lens);
					}
					break;
				}
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.root_view_fragment_image:
			case R.id.image_view_image_framgent:
				getActivity().onBackPressed();
				break;
		}
	}
}
