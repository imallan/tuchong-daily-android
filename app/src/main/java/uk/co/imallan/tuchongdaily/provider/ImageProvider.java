package uk.co.imallan.tuchongdaily.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import uk.co.imallan.tuchongdaily.db.Table;

/**
 * Created by allan on 15/2/25.
 */
public class ImageProvider extends AbstractProvider {

	private static final String POST_TABLE_NAME = Table.Post.TABLE_NAME;

	private static final String TABLE_NAME = Table.Image.TABLE_NAME;

	private static final int URI_IMAGES = 1;

	private static final int URI_IMAGE_ID = 2;

	private static final int URI_IMAGES_POST_ID = 3;


	protected static Uri getContentUri() {
		return Uri.parse(CONTENT_URI_BASE + getAuthority(ImageProvider.class) + "/" + TABLE_NAME);
	}

	public static Uri uriImages() {
		return getContentUri();
	}

	public static Uri uriImage(String id) {
		return Uri.withAppendedPath(getContentUri(), id);
	}

	public static Uri uriPostImages(String id) {
		return Uri.withAppendedPath(Uri.withAppendedPath(getContentUri(), POST_TABLE_NAME), id);
	}

	@Override
	public boolean onCreate() {
		boolean result = super.onCreate();
		final String authority = getAuthority(this.getClass());
		uriMatcher.addURI(authority, TABLE_NAME, URI_IMAGES);
		uriMatcher.addURI(authority, TABLE_NAME + "/#", URI_IMAGE_ID);
		uriMatcher.addURI(authority, TABLE_NAME + "/" + POST_TABLE_NAME + "/#", URI_IMAGES_POST_ID);
		return result;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor c = null;
		final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (uriMatcher.match(uri)) {
			case URI_IMAGE_ID:
				qb.setTables(TABLE_NAME);
				qb.appendWhere(Table.Image.COLUMN_ID + "=\"" + uri.getLastPathSegment() + "\"");
				c = qb.query(getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case URI_IMAGES_POST_ID:
				qb.setTables(TABLE_NAME);
				qb.appendWhere(Table.Image.COLUMN_POST_ID + "=\"" + uri.getLastPathSegment() + "\"");
				c = qb.query(getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
				break;
		}
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case URI_IMAGE_ID:
				return TYPE_ITEM_BASE + TABLE_NAME;
			case URI_IMAGES:
			case URI_IMAGES_POST_ID:
				return TYPE_LIST_BASE + TABLE_NAME;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		switch (uriMatcher.match(uri)) {
			case URI_IMAGES:
			case URI_IMAGE_ID:
				getWritableDatabase().insert(TABLE_NAME, null, values);
				break;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int deleted = 0;
		switch (uriMatcher.match(uri)) {
			case URI_IMAGES:
				deleted = getWritableDatabase().delete(TABLE_NAME, null, null);
				break;
			case URI_IMAGE_ID:
				deleted = getWritableDatabase().delete(TABLE_NAME, Table.Image.COLUMN_POST_ID + "=?", new String[]{uri.getLastPathSegment()});
				break;
		}
		return deleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
