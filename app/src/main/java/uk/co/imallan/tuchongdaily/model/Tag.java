package uk.co.imallan.tuchongdaily.model;

import java.io.Serializable;

/**
 * Created by allan on 15/2/15.
 */
public class Tag implements Serializable {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
