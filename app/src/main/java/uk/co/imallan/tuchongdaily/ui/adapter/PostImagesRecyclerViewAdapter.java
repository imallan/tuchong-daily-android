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

/**
 * Created by allan on 15/2/25.
 */
public class PostImagesRecyclerViewAdapter extends RecyclerView.Adapter<PostImagesRecyclerViewAdapter.ViewHolder> {

	private Context mContext;

	private Cursor mCursor;

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
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (mCursor.moveToPosition(position)) {
			Picasso.with(mContext)
					.load(mCursor.getString(mCursor.getColumnIndex(Table.Image.COLUMN_URL_FULL)))
					.into(holder.image);
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
