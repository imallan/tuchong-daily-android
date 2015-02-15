package uk.co.imallan.tuchongdaily.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by allan on 15/2/15.
 */
public class APIFactory {

	private static final String BASE_URL = "http://tuchong.imallan.co.uk:5000";

	private static API instance;

	private static Gson gson = new GsonBuilder()
			.setExclusionStrategies(new ExclusionStrategy() {
				@Override
				public boolean shouldSkipField(FieldAttributes f) {
					return f.getDeclaringClass().equals(RealmObject.class);
				}

				@Override
				public boolean shouldSkipClass(Class<?> clazz) {
					return false;
				}
			})
			.create();

	public static API instance() {
		if (instance == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(BASE_URL)
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.setConverter(new GsonConverter(gson)).build();
			instance = new API(restAdapter.create(APIGet.class));
		}
		return instance;
	}

}
