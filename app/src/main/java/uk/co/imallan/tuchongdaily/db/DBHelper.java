package uk.co.imallan.tuchongdaily.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allan on 15/2/20.
 */
public class DBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "TuchongDaily.db";

	private static DBHelper instance;

	private Context context;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static DBHelper instance(Context context) {
		if (instance == null) {
			Context appContext = context.getApplicationContext();
			instance = new DBHelper(appContext);
			instance.context = appContext;
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Table.Post.SQL_CREATE_TABLE);
		db.execSQL(Table.Author.SQL_CREATE_TABLE);
		db.execSQL(Table.Image.SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
