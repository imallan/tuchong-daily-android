package uk.co.imallan.tuchongdaily.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import uk.co.imallan.tuchongdaily.service.ServiceReceiver;

/**
 * Created by allan on 15/2/25.
 */
public abstract class AbstractFragment extends Fragment {

	protected ServiceReceiver serviceReceiver;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (this.prepareServiceReceiver()) {
			serviceReceiver = new ServiceReceiver(new Handler());
		}
	}

	protected abstract boolean prepareServiceReceiver();

}
