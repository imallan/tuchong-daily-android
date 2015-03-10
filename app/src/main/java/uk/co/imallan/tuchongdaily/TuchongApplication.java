package uk.co.imallan.tuchongdaily;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

/**
 * Created by allan on 15/3/8.
 */
public class TuchongApplication extends Application {

	private static final String PICASSO_CACHE = "picasso-cache";

	private static final long PICASSO_CACHE_SIZE = 100 * 1024 * 1024;

	public void onCreate() {
		super.onCreate();
		if (BuildConfig.DEBUG) {
			Stetho.initialize(
					Stetho.newInitializerBuilder(this)
							.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
							.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
							.build());
			setPicasso();
		}
	}

	public void setPicasso() {
		OkHttpClient client = new OkHttpClient();
		client.networkInterceptors().add(new StethoInterceptor());
		File cache = new File(this.getCacheDir(), PICASSO_CACHE);
		if (!cache.exists()) {
			//noinspection ResultOfMethodCallIgnored
			cache.mkdirs();
		}
		try {
			client.setCache(new Cache(cache, PICASSO_CACHE_SIZE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Picasso picasso = new Picasso.Builder(this)
				.downloader(new OkHttpDownloader(client))
				.build();
		Picasso.setSingletonInstance(picasso);
	}

}
