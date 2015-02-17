package uk.co.imallan.tuchongdaily.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import uk.co.imallan.tuchongdaily.R;
import uk.co.imallan.tuchongdaily.api.data.DataPosts;
import uk.co.imallan.tuchongdaily.model.Post;
import uk.co.imallan.tuchongdaily.service.PostsService;
import uk.co.imallan.tuchongdaily.service.ServiceReceiver;


public class MainActivity extends AbstractActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PostsService.requestPosts(this, serviceReceiver, 0, 12);
	}

	@Override
	boolean prepareServiceReceiver() {
		return true;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData, Intent originalIntent, Bundle originalBundle) {
		switch (resultCode) {
			case ServiceReceiver.STATUS_DATA:
				DataPosts dataPosts = (DataPosts) resultData.getSerializable(ServiceReceiver.EXTRA_DATA);
				for (Post post : dataPosts.getPosts()) {
					Log.v("POST", post.getTitle());
				}
				break;
		}
	}
}
