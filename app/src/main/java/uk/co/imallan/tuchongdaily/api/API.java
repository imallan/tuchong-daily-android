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

	public Response<DataPosts> getPosts() {
		return api.getPosts(
				Settings.APP_SECRET,
				0,
				0
		);
	}
}
