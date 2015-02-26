package uk.co.imallan.tuchongdaily.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.db.Table;
import uk.co.imallan.tuchongdaily.ui.activity.ImagePinchZoomActivity;

/**
 * Created by allan on 15/2/25.
 */
public class PostImagesRecyclerViewAdapter extends RecyclerView.Adapter<PostImagesRecyclerViewAdapter.ViewHolder> {

	private Context mContext;

	private Cursor mCursor;

	private long mLastClick = 0;

	public PostImagesRecyclerViewAdapter(Context context) {
		this.mContext = context;
	}

	public Cursor swapCursor(Cursor cursor) {
		Cursor oldCursor = mCursor;
		mCursor = cursor;
		notifyDataSetChanged();
		return oldCursor;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View itemView = inflater.inflate(R.layout.view_item_post_image, parent, false);
		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		if (mCursor.moveToPosition(position)) {
			final String fullImageURL = mCursor.getString(mCursor.getColumnIndex(Table.Image.COLUMN_URL_LARGE));
			Picasso.with(mContext)
					.load(fullImageURL)
					.into(holder.image);
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					long current = System.currentTimeMillis();
					if (current - mLastClick <= 1000) {
						return;
					}
					mLastClick = current;
					ImagePinchZoomActivity.startActivity(mContext, fullImageURL, holder.image, null);
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return mCursor == null ? 0 : mCursor.getCount();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		ImageView image;

		public ViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.image_post_image);
		}
	}
}
