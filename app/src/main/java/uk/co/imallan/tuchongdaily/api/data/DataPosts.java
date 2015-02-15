package uk.co.imallan.tuchongdaily.api.data;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import uk.co.imallan.tuchongdaily.model.Post;

/**
 * Created by allan on 15/1/30.
 */
public class DataPosts extends RealmObject implements Serializable {

	private RealmList<Post> posts;

	public RealmList<Post> getPosts() {
		return posts;
	}

	public void setPosts(RealmList<Post> posts) {
		this.posts = posts;
	}
}
