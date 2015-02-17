package uk.co.imallan.tuchongdaily.api;

import retrofit.RestAdapter;

/**
 * Created by allan on 15/2/15.
 */
public class APIFactory {

	private static final String BASE_URL = "http://tuchong.imallan.co.uk:5000";

	private static API instance;

	public static API instance() {
		if (instance == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(BASE_URL)
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.build();
			instance = new API(restAdapter.create(APIGet.class));
		}
		return instance;
	}

}
