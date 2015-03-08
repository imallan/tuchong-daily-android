package uk.co.imallan.tuchongdaily.api;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import uk.co.imallan.tuchongdaily.BuildConfig;

/**
 * Created by allan on 15/2/15.
 */
public class APIFactory {

	private static final String BASE_URL = "http://tuchong.imallan.co.uk:5000";

	private static API instance;

	public static API instance() {
		if (instance == null) {
			OkHttpClient client = new OkHttpClient();
			if (BuildConfig.DEBUG) {
				client.networkInterceptors().add(new StethoInterceptor());
			}

			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(BASE_URL)
					.setClient(new OkClient(client))
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.build();
			instance = new API(restAdapter.create(APIGet.class));
		}
		return instance;
	}

}
