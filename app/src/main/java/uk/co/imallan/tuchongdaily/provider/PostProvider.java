package uk.co.imallan.tuchongdaily.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import uk.co.imallan.tuchongdaily.db.Table;

/**
 * Created by allan on 15/2/20.
 */
public class PostProvider extends AbstractProvider {

	private static final String TABLE_NAME = Table.Post.TABLE_NAME;

	private static final int URI_PRODUCTS = 1;

	private static final int URI_PRODUCT_ID = 2;


	protected static Uri getContentUri() {
		return Uri.parse(CONTENT_URI_BASE + getAuthority(PostProvider.class) + "/" + TABLE_NAME);
	}

	public static Uri uriPosts() {
		return getContentUri();
	}

	public static Uri uriPost(String id) {
		return Uri.withAppendedPath(Uri.withAppendedPath(getContentUri(), URI_ID), id);
	}

	@Override
	public boolean onCreate() {
		boolean result = super.onCreate();
		final String authority = getAuthority(this.getClass());
		uriMatcher.addURI(authority, TABLE_NAME, URI_PRODUCTS);
		uriMatcher.addURI(authority, TABLE_NAME + "/#", URI_PRODUCT_ID);
		return result;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor c = null;
		final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (uriMatcher.match(uri)) {
			case URI_PRODUCTS:
				qb.setTables(TABLE_NAME
								+ " JOIN " + Table.Author.TABLE_NAME +
								" ON " + Table.Author.COLUMN_ID + "=" + Table.Post.COLUMN_AUTHOR_ID
				);
				c = qb.query(getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case URI_PRODUCT_ID:
				qb.setTables(TABLE_NAME
								+ " JOIN " + Table.Author.TABLE_NAME +
								" ON " + Table.Author.COLUMN_ID + "=" + Table.Post.COLUMN_AUTHOR_ID
				);
				qb.appendWhere(Table.Post.COLUMN_ID + "=\"" + uri.getLastPathSegment() + "\"");
				c = qb.query(getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
				break;
		}
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case URI_PRODUCTS:
				return TYPE_LIST_BASE + TABLE_NAME;
			case URI_PRODUCT_ID:
				return TYPE_ITEM_BASE + TABLE_NAME;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		switch (uriMatcher.match(uri)) {
			case URI_PRODUCTS:
				getWritableDatabase().insert(TABLE_NAME, null, values);
				break;
			case URI_PRODUCT_ID:
				getWritableDatabase().insert(TABLE_NAME, null, values);
				break;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int deleted = 0;
		switch (uriMatcher.match(uri)) {
			case URI_PRODUCTS:
				deleted = getWritableDatabase().delete(TABLE_NAME, null, null);
				break;
			case URI_PRODUCT_ID:
				deleted = getWritableDatabase().delete(TABLE_NAME, Table.Post.COLUMN_ID + "=?", new String[]{uri.getLastPathSegment()});
				break;
		}
		return deleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
