package uk.co.imallan.tuchongdaily.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by allan on 15/2/6.
 */
public class Author extends RealmObject implements Serializable {

	private String name;

	private String domain;

	private String url;

	private String type;

	private String id;

	private String icon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
