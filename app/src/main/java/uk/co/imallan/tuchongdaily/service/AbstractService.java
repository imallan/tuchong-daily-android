package uk.co.imallan.tuchongdaily.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.Serializable;

import uk.co.imallan.tuchongdaily.utils.DeviceUtils;

/**
 * Created by allan on 15/2/17.
 */
public abstract class AbstractService extends IntentService {

	private static final String LOG_TAG = "SERVICE";

	public static final String EXTRA_REQUEST_TYPE = "EXTRA_REQUEST_TYPE";

	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */
	public AbstractService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent.hasExtra(ServiceReceiver.EXTRA_RECEIVER)) {
			final ResultReceiver receiver = intent.getParcelableExtra(ServiceReceiver.EXTRA_RECEIVER);
			Bundle bundle = new Bundle();
			bundle.putParcelable(ServiceReceiver.EXTRA_ORIGINAL_INTENT, intent);
			receiver.send(ServiceReceiver.STATUS_RUNNING, bundle);
			try {
				if (!DeviceUtils.isNetworkAvailable(this)) {
					throw new ServiceException(ServiceException.ERROR_NOT_INTERNET_CONNECTION,
							"There is not internet connection. Service wont be executed");
				}
				onHandleServiceIntent(intent, bundle, receiver);
				receiver.send(ServiceReceiver.STATUS_FINISHED, bundle);
			} catch (ServiceException e) {
				Log.e(LOG_TAG, e.getMessage(), e);
				bundle.putString(ServiceReceiver.EXTRA_ERROR_MESSAGE, e.getMessage());
				bundle.putString(ServiceReceiver.EXTRA_ERROR_EXCEPTION_NAME, e.getClass().getCanonicalName());
				bundle.putInt(ServiceReceiver.EXTRA_ERROR_CODE, e.getErrorCode());
				receiver.send(ServiceReceiver.STATUS_ERROR, bundle);
			} catch (Exception e) {
				Log.e(LOG_TAG, e.getMessage(), e);
				e.printStackTrace();
				bundle.putString(ServiceReceiver.EXTRA_ERROR_MESSAGE, e.getMessage());
				bundle.putString(ServiceReceiver.EXTRA_ERROR_EXCEPTION_NAME, e.getClass().getCanonicalName());
				receiver.send(ServiceReceiver.STATUS_ERROR, bundle);
			}
		} else {
			try {
				onHandleServiceIntent(intent, null, null);
			} catch (Exception e) {
				Log.e(LOG_TAG, e.getMessage(), e);
			}
		}

	}

	/**
	 * Sends data to the receiver, usually the creator of the service intent.
	 *
	 * @param receiver
	 * @param originalIntent
	 * @param data
	 */
	protected void sendDataToReceiver(ResultReceiver receiver, Intent originalIntent, Serializable data) {
		if (receiver != null && data != null) {
			Bundle bundle = new Bundle();
			bundle.putParcelable(ServiceReceiver.EXTRA_ORIGINAL_INTENT, originalIntent);
			bundle.putSerializable(ServiceReceiver.EXTRA_DATA, data);
			receiver.send(ServiceReceiver.STATUS_DATA, bundle);
		}
	}

	protected abstract void onHandleServiceIntent(Intent intent, Bundle results, ResultReceiver receiver);
}
