package uk.co.imallan.tuchongdaily.api.data;

import java.io.Serializable;
import java.util.List;

import uk.co.imallan.tuchongdaily.db.model.Post;

/**
 * Created by allan on 15/1/30.
 */
public class DataPosts extends BaseData {

	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
