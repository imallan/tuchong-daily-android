package uk.co.imallan.tuchongdaily.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;

import uk.co.imallan.tuchongdaily.db.DBHelper;

/**
 * Created by allan on 15/2/20.
 */
abstract public class AbstractModel {

	protected String tableName;

	protected ContentValues modelValues;

	protected ArrayList<AbstractModel> nestedModels;

	protected AbstractModel(String tableName) {
		this.tableName = tableName;
	}

	abstract void saveToTable(ContentValues contentValues);

	abstract void saveNestedModels(ArrayList<AbstractModel> nestedModels);

	public void save(Context context) {
		modelValues = new ContentValues();
		nestedModels = new ArrayList<AbstractModel>() {
		};
		saveToTable(modelValues);
		saveNestedModels(nestedModels);
		if (TextUtils.isEmpty(tableName)) {
			throw new RuntimeException("A Table name must be assigned to the model before saving");
		}
		SQLiteDatabase db = DBHelper.instance(context).getWritableDatabase();
		if (modelValues != null) {
			db.insert(tableName, null, modelValues);
		}
		if (nestedModels != null && nestedModels.size() > 0) {
			for (AbstractModel model : nestedModels) {
				model.save(context);
			}
		}
	}
}
