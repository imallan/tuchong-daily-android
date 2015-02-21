package uk.co.imallan.tuchongdaily.model;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.ArrayList;

import uk.co.imallan.tuchongdaily.db.Table;

/**
 * Created by allan on 15/2/6.
 */
public class Author extends AbstractModel implements Serializable {

	private String name;

	private String domain;

	private String url;

	private String type;

	private String id;

	private String icon;

	protected Author() {
		super(Table.Author.TABLE_NAME);
	}

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

	@Override
	void saveToTable(ContentValues modeValues) {
		modelValues.put(Table.Author.COLUMN_NAME, name);
		modelValues.put(Table.Author.COLUMN_DOMAIN, domain);
		modelValues.put(Table.Author.COLUMN_URL, url);
		modelValues.put(Table.Author.COLUMN_TYPE, type);
		modelValues.put(Table.Author.COLUMN_ID, id);
		modelValues.put(Table.Author.COLUMN_ICON, icon);
	}

	@Override
	void saveNestedModels(ArrayList<AbstractModel> nestedModels) {

	}
}
