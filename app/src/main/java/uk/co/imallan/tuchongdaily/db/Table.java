package uk.co.imallan.tuchongdaily.db;

import android.provider.BaseColumns;

/**
 * Created by allan on 15/2/20.
 */
public final class Table {

	public Table() {
	}

	public static String UNDER_LINE = "_";

	public static final class Post implements BaseColumns {

		public static final String TABLE_NAME = "post";

		public static final String COLUMN_ID = TABLE_NAME + UNDER_LINE + "id";

		public static final String COLUMN_LAST_TREND = TABLE_NAME + UNDER_LINE + "lasttrend";

		public static final String COLUMN_URL = TABLE_NAME + UNDER_LINE + "url";

		public static final String COLUMN_TAGS = TABLE_NAME + UNDER_LINE + "tags";

		public static final String COLUMN_EXCERPT = TABLE_NAME + UNDER_LINE + "excerpt";

		public static final String COLUMN_AUTHOR_ID = TABLE_NAME + UNDER_LINE + "authorid";

		public static final String COLUMN_TYPE = TABLE_NAME + UNDER_LINE + "type";

		public static final String SQL_CREATE_TABLE =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_ID + " TEXT UNIQUE ON CONFLICT REPLACE," +
						COLUMN_LAST_TREND + " INTEGER NOT NULL," +
						COLUMN_URL + " TEXT," +
						COLUMN_TAGS + " TEXT," +
						COLUMN_EXCERPT + " TEXT," +
						COLUMN_AUTHOR_ID + " TEXT NOT NULL," +
						COLUMN_TYPE + " TEXT " +
						" )";

		public static final String SQL_DELETE_TABLE =
				"DROP TABLE IF EXISTS " + TABLE_NAME;
	}

	public static final class Author implements BaseColumns {

		public static final String TABLE_NAME = "author";

		public static final String COLUMN_ID = TABLE_NAME + UNDER_LINE + "id";

		public static final String COLUMN_NAME = TABLE_NAME + UNDER_LINE + "name";

		public static final String COLUMN_DOMAIN = TABLE_NAME + UNDER_LINE + "domain";

		public static final String COLUMN_URL = TABLE_NAME + UNDER_LINE + "url";

		public static final String COLUMN_TYPE = TABLE_NAME + UNDER_LINE + "type";

		public static final String COLUMN_ICON = TABLE_NAME + UNDER_LINE + "icon";

		public static final String SQL_CREATE_TABLE =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_ID + " TEXT UNIQUE ON CONFLICT REPLACE," +
						COLUMN_NAME + " TEXT NOT NULL," +
						COLUMN_DOMAIN + " TEXT," +
						COLUMN_URL + " TEXT," +
						COLUMN_TYPE + " TEXT," +
						COLUMN_ICON + " TEXT" +
						")";

		public static final String SQL_DELETE_TABLE =
				"DROP TABLE IF EXISTS " + TABLE_NAME;

	}

	public static final class Image implements BaseColumns {

		public static final String TABLE_NAME = "image";

		public static final String COLUMN_ID = TABLE_NAME + UNDER_LINE + "id";

		public static final String COLUMN_TITLE = TABLE_NAME + UNDER_LINE + "title";

		public static final String COLUMN_DESCRIPTION = TABLE_NAME + UNDER_LINE + "description";

		public static final String COLUMN_URL_SMALL = TABLE_NAME + UNDER_LINE + "urlsmall";

		public static final String COLUMN_URL_MEDIUM = TABLE_NAME + UNDER_LINE + "urlmedium";

		public static final String COLUMN_URL_LARGE = TABLE_NAME + UNDER_LINE + "urllarge";

		public static final String COLUMN_URL_SQUARE = TABLE_NAME + UNDER_LINE + "urlsquare";

		public static final String COLUMN_URL_FULL = TABLE_NAME + UNDER_LINE + "url";

		public static final String COLUMN_CAMERA = TABLE_NAME + UNDER_LINE + "camera";

		public static final String COLUMN_TAKEN = TABLE_NAME + UNDER_LINE + "taken";

		public static final String COLUMN_LENS = TABLE_NAME + UNDER_LINE + "lens";

		public static final String COLUMN_EXPOSURE = TABLE_NAME + UNDER_LINE + "exposure";

		public static final String COLUMN_EXCERPT = TABLE_NAME + UNDER_LINE + "excerpt";

		public static final String COLUMN_USER_ID = TABLE_NAME + UNDER_LINE + "userid";

		public static final String COLUMN_POST_ID = TABLE_NAME + UNDER_LINE + "postid";

		public static final String SQL_CREATE_TABLE =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_ID + " TEXT UNIQUE ON CONFLICT REPLACE," +
						COLUMN_TITLE + " TEXT," +
						COLUMN_DESCRIPTION + " TEXT," +
						COLUMN_URL_SMALL + " TEXT," +
						COLUMN_URL_MEDIUM + " TEXT," +
						COLUMN_URL_LARGE + " TEXT," +
						COLUMN_URL_SQUARE + " TEXT," +
						COLUMN_URL_FULL + " TEXT," +
						COLUMN_CAMERA + " TEXT," +
						COLUMN_EXPOSURE + " TEXT," +
						COLUMN_TAKEN + " TEXT," +
						COLUMN_LENS + " TEXT," +
						COLUMN_EXCERPT + " TEXT," +
						COLUMN_USER_ID + " TEXT NOT NULL," +
						COLUMN_POST_ID + " TEXT NOT NULL" +
						")";

		public static final String SQL_DELETE_TABLE =
				"DROP TABLE IF EXISTS " + TABLE_NAME;

	}

}
