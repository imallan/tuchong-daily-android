package uk.co.imallan.tuchongdaily.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import uk.co.imallan.tuchongdaily.api.APIFactory;
import uk.co.imallan.tuchongdaily.api.data.DataPosts;

/**
 * Created by allan on 15/2/17.
 */
public class PostsService extends AbstractService {

	private static final String EXTRA_SKIP = "EXTRA_SKIP";

	private static final String EXTRA_LIMIT = "EXTRA_LIMIT";

	public PostsService() {
		super("PostsService");
	}

	public static void requestPosts(Context context, ServiceReceiver receiver, int limit, int skip) {
		Intent intent = new Intent(context, PostsService.class);
		ServiceReceiver.addServiceReceiver(intent, receiver);
		intent.putExtra(EXTRA_REQUEST_TYPE, REQUESTS.GET_POSTS);
		intent.putExtra(EXTRA_SKIP, skip);
		intent.putExtra(EXTRA_LIMIT, limit);
		context.startService(intent);
	}

	@Override
	protected void onHandleServiceIntent(Intent intent, Bundle results, ResultReceiver receiver) {
		REQUESTS request = (REQUESTS) intent.getSerializableExtra(EXTRA_REQUEST_TYPE);
		switch (request) {
			case GET_POSTS:
				handleRequestPosts(intent, receiver, intent.getIntExtra(EXTRA_SKIP, 0), intent.getIntExtra(EXTRA_LIMIT, 12));
				break;
		}
	}

	private void handleRequestPosts(Intent originalIntent, ResultReceiver receiver, int skip, int limit) {
		DataPosts dataPosts = APIFactory.instance().getPosts(skip, limit).getData();
		sendDataToReceiver(receiver, originalIntent, dataPosts);
	}

	public enum REQUESTS {
		GET_POSTS
	}
}
