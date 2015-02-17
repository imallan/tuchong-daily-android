package uk.co.imallan.tuchongdaily.api;

import uk.co.imallan.tuchongdaily.Settings;
import uk.co.imallan.tuchongdaily.api.data.DataPosts;

/**
 * Created by allan on 15/2/15.
 */
public class API {

	private APIGet api;

	public API(APIGet apiGet) {
		this.api = apiGet;
	}

	public Response<DataPosts> getPosts(int skip, int limit) {
		return api.getPosts(
				Settings.APP_SECRET,
				skip,
				limit
		);
	}
}
