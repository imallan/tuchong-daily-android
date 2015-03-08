package uk.co.imallan.tuchongdaily;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by allan on 15/3/8.
 */
public class TuchongApplication extends Application {

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
		Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(client)).build();
		Picasso.setSingletonInstance(picasso);
	}

}
