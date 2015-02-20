package uk.co.imallan.tuchongdaily.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import uk.co.imallan.tuchongdaily.db.DBHelper;

/**
 * Created by allan on 15/2/20.
 */
public abstract class AbstractProvider extends ContentProvider {

	protected static final String CONTENT_URI_BASE = "content://";

	protected static final String TYPE_LIST_BASE = "vnd.android.cursor.dir/vnd.tuchongdaily.";

	protected static final String TYPE_ITEM_BASE = "vnd.android.cursor.item/vnd.tuchongdaily.";

	protected static final String URI_ID = "id";

	protected UriMatcher uriMatcher;

	private DBHelper dbHelper;

	private SQLiteDatabase dbRead;

	private SQLiteDatabase dbWrite;

	private static UriMatcher buildUriMatcher() {
		return new UriMatcher(UriMatcher.NO_MATCH);
	}

	public static String getAuthority(Class objectClass) {
		return objectClass.getCanonicalName();
	}

	@Override
	public boolean onCreate() {
		dbHelper = DBHelper.instance(getContext());
		uriMatcher = buildUriMatcher();
		return true;
	}

	protected SQLiteDatabase getReadableDatabase() {
		if (dbRead == null || !dbRead.isOpen()) {
			dbRead = dbHelper.getReadableDatabase();
		}
		return dbRead;
	}

	protected SQLiteDatabase getWritableDatabase() {
		if (dbWrite == null || !dbWrite.isOpen()) {
			dbWrite = dbHelper.getWritableDatabase();
		}
		return dbWrite;
	}

	@Override
	public int bulkInsert(Uri uri, @SuppressWarnings("NullableProblems") ContentValues[] values) {
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		int insertedOrUpdated = super.bulkInsert(uri, values);
		db.setTransactionSuccessful();
		db.endTransaction();
		return insertedOrUpdated;
	}
}
