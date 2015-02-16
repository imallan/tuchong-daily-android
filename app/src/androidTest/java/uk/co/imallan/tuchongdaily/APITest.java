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

	private static final String LOG_TAG = APITest.class.getSimpleName();

	public void testAPIGetPosts() throws Exception {
		Response<DataPosts> posts = APIFactory.instance().getPosts();
		Log.v(LOG_TAG, String.valueOf(posts.getData().getPosts().size()));
		Log.v(LOG_TAG, posts.getData().getPosts().get(0).getTags());
	}
}
