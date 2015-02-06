package uk.co.imallan.tuchongdaily.db.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 15/2/6.
 */
public class ObjectId extends BaseObject{

	@SerializedName("$oid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
