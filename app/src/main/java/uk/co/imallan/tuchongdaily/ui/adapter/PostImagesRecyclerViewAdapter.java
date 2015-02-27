package uk.co.imallan.tuchongdaily.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.db.Table;
import uk.co.imallan.tuchongdaily.ui.activity.ImageActivity;

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
		if (mCursor.moveToPosition(position + 1)) {
			if (position == 0) {
				holder.itemView.setPadding(
						0,
						0,
						0,
						0
				);
				holder.image.requestLayout();
			} else {
				holder.itemView.setPadding(
						(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, mContext.getResources().getDisplayMetrics()),
						0,
						0,
						0
				);
				holder.image.requestLayout();

			}
			final int width = mCursor.getInt(mCursor.getColumnIndex(Table.Image.COLUMN_WIDTH));
			final int height = mCursor.getInt(mCursor.getColumnIndex(Table.Image.COLUMN_HEIGHT));
			if (width != 0 && height != 0) {
				final float ratio = width / (float) height;
				holder.image.post(new Runnable() {
					@Override
					public void run() {
						holder.image.getLayoutParams().width = (int) (holder.image.getHeight() * ratio);
						holder.image.requestLayout();
					}
				});
			}
			final String imageURL = mCursor.getString(mCursor.getColumnIndex(Table.Image.COLUMN_URL_MEDIUM));
			final String largeImageURL = mCursor.getString(mCursor.getColumnIndex(Table.Image.COLUMN_URL_LARGE));
			Picasso.with(mContext)
					.load(imageURL)
					.into(holder.image);
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					long current = System.currentTimeMillis();
					if (current - mLastClick <= 1000) {
						return;
					}
					mLastClick = current;
					ImageActivity.startActivity(mContext, largeImageURL, holder.image);
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return mCursor == null ? 0 : (mCursor.getCount() - 1);
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		ImageView image;

		public ViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.image_post_image);
		}
	}
}
