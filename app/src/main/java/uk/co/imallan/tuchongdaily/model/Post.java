package uk.co.imallan.tuchongdaily.model;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import uk.co.imallan.tuchongdaily.db.Table;


/**
 * Created by allan on 15/1/30.
 */
public class Post extends AbstractModel implements Serializable {

	private String id;

	@SerializedName("_id")
	private ObjectId objectId;

	private long lastTrend;

	private Author author;

	private String title;

	private String url;

	private String tags;

	private String excerpt;

	private String publishedAt;

	private String authorId;

	private String siteId;

	private ArrayList<Image> images;

	private String type;

	protected Post() {
		super(Table.Post.TABLE_NAME);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public long getLastTrend() {
		return lastTrend;
	}

	public void setLastTrend(long lastTrend) {
		this.lastTrend = lastTrend;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	void saveToTable(ContentValues contentValues) {
		contentValues.put(Table.Post.COLUMN_ID, id);
		contentValues.put(Table.Post.COLUMN_LAST_TREND, lastTrend);
		contentValues.put(Table.Post.COLUMN_URL, url);
		contentValues.put(Table.Post.COLUMN_TAGS, tags);
		contentValues.put(Table.Post.COLUMN_EXCERPT, excerpt);
		contentValues.put(Table.Post.COLUMN_AUTHOR_ID, authorId);
		contentValues.put(Table.Post.COLUMN_TYPE, type);
	}

	@Override
	void saveNestedModels(ArrayList<AbstractModel> nestedModels) {
		nestedModels.add(author);
		for (Image image : images) {
			nestedModels.add(image);
		}

	}
}
