package uk.co.imallan.tuchongdaily.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewTreeObserver;

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


	public void scheduleStartPostponedTransition(final View sharedElement) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			sharedElement.getViewTreeObserver().addOnPreDrawListener(
					new ViewTreeObserver.OnPreDrawListener() {
						@SuppressLint("NewApi")
						@Override
						public boolean onPreDraw() {
							sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
							getActivity().startPostponedEnterTransition();
							return true;
						}
					});
		}
	}
}
