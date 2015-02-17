package uk.co.imallan.tuchongdaily.api.data;

import java.io.Serializable;
import java.util.ArrayList;

import uk.co.imallan.tuchongdaily.model.Post;

/**
 * Created by allan on 15/1/30.
 */
public class DataPosts implements Serializable {

	private ArrayList<Post> posts;

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
}
