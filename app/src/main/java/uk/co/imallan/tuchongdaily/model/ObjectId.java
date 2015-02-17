package uk.co.imallan.tuchongdaily.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by allan on 15/2/6.
 */
public class ObjectId implements Serializable {

	@SerializedName("$oid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
