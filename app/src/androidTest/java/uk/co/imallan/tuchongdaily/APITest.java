package uk.co.imallan.tuchongdaily;

import android.test.AndroidTestCase;
import android.util.Log;

import uk.co.imallan.tuchongdaily.api.APIFactory;
import uk.co.imallan.tuchongdaily.api.Response;
import uk.co.imallan.tuchongdaily.api.data.DataPosts;

/**
 * Created by allan on 15/2/15.
 */
public class APITest extends AndroidTestCase {


	public void testAPIGetPosts() throws Exception {
		Response<DataPosts> posts = APIFactory.instance().getPosts();
		Log.v("POSTS", String.valueOf(posts.getData().getPosts().size()));
	}
}
