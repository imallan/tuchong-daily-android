package uk.co.imallan.tuchongdaily.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import uk.co.imallan.tuchongdaily.service.ServiceReceiver;

/**
 * Created by allan on 15/2/17.
 */
public abstract class AbstractActivity extends ActionBarActivity implements ServiceReceiver.Receiver {

	protected ServiceReceiver serviceReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (this.prepareServiceReceiver()) {
			serviceReceiver = new ServiceReceiver(new Handler());
		}
	}

	abstract boolean prepareServiceReceiver();

	@Override
	protected void onResume() {
		super.onResume();
		if (serviceReceiver != null) {
			serviceReceiver.setReceiver(this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (serviceReceiver != null) {
			serviceReceiver.setReceiver(null);
		}
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData, Intent originalIntent, Bundle originalBundle) {

	}
}
