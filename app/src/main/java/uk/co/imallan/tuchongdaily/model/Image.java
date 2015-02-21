package uk.co.imallan.tuchongdaily.model;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import uk.co.imallan.tuchongdaily.db.Table;

/**
 * Created by allan on 15/2/6.
 */
public class Image extends AbstractModel implements Serializable {

	private String id;

	private String title;

	private String description;

	@SerializedName("url_small")
	private String urlSmall;

	@SerializedName("url_medium")
	private String urlMedium;

	@SerializedName("url_large")
	private String urlLarge;

	@SerializedName("url_square")
	private String urlSquare;

	@SerializedName("url_full")
	private String url;

	private int height;

	private int width;

	private String camera;

	private String taken;

	private String userId;

	private String postId;

	private String exposure;

	private String lens;

	private String excerpt;

	protected Image() {
		super(Table.Image.TABLE_NAME);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlSmall() {
		return urlSmall;
	}

	public void setUrlSmall(String urlSmall) {
		this.urlSmall = urlSmall;
	}

	public String getUrlMedium() {
		return urlMedium;
	}

	public void setUrlMedium(String urlMedium) {
		this.urlMedium = urlMedium;
	}

	public String getUrlLarge() {
		return urlLarge;
	}

	public void setUrlLarge(String urlLarge) {
		this.urlLarge = urlLarge;
	}

	public String getUrlSquare() {
		return urlSquare;
	}

	public void setUrlSquare(String urlSquare) {
		this.urlSquare = urlSquare;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getTaken() {
		return taken;
	}

	public void setTaken(String taken) {
		this.taken = taken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getExposure() {
		return exposure;
	}

	public void setExposure(String exposure) {
		this.exposure = exposure;
	}

	public String getLens() {
		return lens;
	}

	public void setLens(String lens) {
		this.lens = lens;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	@Override
	void saveToTable(ContentValues modelValues) {
		modelValues.put(Table.Image.COLUMN_ID, id);
		modelValues.put(Table.Image.COLUMN_TITLE, title);
		modelValues.put(Table.Image.COLUMN_URL_SMALL, urlSmall);
		modelValues.put(Table.Image.COLUMN_URL_MEDIUM, urlMedium);
		modelValues.put(Table.Image.COLUMN_URL_LARGE, urlLarge);
		modelValues.put(Table.Image.COLUMN_URL_SQUARE, urlSquare);
		modelValues.put(Table.Image.COLUMN_URL_FULL, url);
		modelValues.put(Table.Image.COLUMN_CAMERA, camera);
		modelValues.put(Table.Image.COLUMN_TAKEN, taken);
		modelValues.put(Table.Image.COLUMN_USER_ID, userId);
		modelValues.put(Table.Image.COLUMN_POST_ID, postId);
		modelValues.put(Table.Image.COLUMN_EXPOSURE, exposure);
		modelValues.put(Table.Image.COLUMN_LENS, lens);
		modelValues.put(Table.Image.COLUMN_EXCERPT, excerpt);
	}

	@Override
	void saveNestedModels(ArrayList<AbstractModel> nestedModels) {

	}
}
