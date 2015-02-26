package uk.co.imallan.tuchongdaily.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.db.Table;
import uk.co.imallan.tuchongdaily.provider.ImageProvider;
import uk.co.imallan.tuchongdaily.provider.PostProvider;
import uk.co.imallan.tuchongdaily.ui.adapter.PostImagesRecyclerViewAdapter;

/**
 * Created by allan on 15/2/25.
 */
public class PostFragment extends AbstractFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	public static final String POST_SERVER_ID = "POST_SERVER_ID";

	private static final int LOADER_POST = 1;

	private static final int LOADER_POST_IMAGES = 2;

	private String mServerId;

	private TextView mTitle;

	private ImageView mImage;

	private RecyclerView mRecyclerView;

	private PostImagesRecyclerViewAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		mServerId = arguments.getString(POST_SERVER_ID);
	}

	@Override
	protected boolean prepareServiceReceiver() {
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_post, container, false);
		mTitle = (TextView) rootView.findViewById(R.id.text_post_title);
		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_post_images);
		mImage = (ImageView) rootView.findViewById(R.id.image_post);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
		mAdapter = new PostImagesRecyclerViewAdapter(getActivity());
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.setItemViewCacheSize(5);
		getLoaderManager().initLoader(LOADER_POST_IMAGES, null, this);
		getLoaderManager().initLoader(LOADER_POST, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		switch (id) {
			case LOADER_POST_IMAGES:
				return new CursorLoader(getActivity(), ImageProvider.uriPostImages(mServerId), null, null, null, null);
			case LOADER_POST:
				return new CursorLoader(getActivity(), PostProvider.uriPost(mServerId), null, null, null, null);
			default:
				return null;
		}
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		switch (loader.getId()) {
			case LOADER_POST_IMAGES:
				if (data.moveToPosition(0)) {
					String url = data.getString(data.getColumnIndex(Table.Image.COLUMN_URL_FULL));
					Picasso.with(getActivity()).load(url).fit().centerCrop().into(mImage);
				}
				mAdapter.swapCursor(data);
				break;
			case LOADER_POST:
				if (data.moveToPosition(0)) {
					String title = data.getString(data.getColumnIndex(Table.Post.COLUMN_EXCERPT));
					mTitle.setText(title);
				}
				break;
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}
}
