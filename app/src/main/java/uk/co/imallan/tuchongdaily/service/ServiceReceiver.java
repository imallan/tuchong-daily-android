package uk.co.imallan.tuchongdaily.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.util.LinkedList;
import java.util.List;

/**
 * Services receiver handles the communication between the services and the activities.
 */
public class ServiceReceiver extends ResultReceiver {

	public static final int STATUS_RUNNING = 101;

	public static final int STATUS_FINISHED = 102;

	public static final int STATUS_ERROR = 103;

	public static final int STATUS_DATA = 104;

	public static final String EXTRA_DATA = "EXTRA_DATA";

	public static final String EXTRA_ERROR_MESSAGE = "ERROR_MESSAGE";

	public static final String EXTRA_RECEIVER = "EXTRA_RECEIVER";

	public static final String EXTRA_ORIGINAL_INTENT = "EXTRA_ORIGINAL_INTENT";

	public static final String EXTRA_ERROR_EXCEPTION_NAME = "EXTRA_ERROR_EXCEPTION_NAME";

	public static final String EXTRA_ERROR_CODE = "EXTRA_ERROR_CODE";

	private final List<StoreResult> storedResults;

	private Receiver receiver;

	public ServiceReceiver(Handler handler) {
		super(handler);
		storedResults = new LinkedList<StoreResult>();
	}

	public static void addServiceReceiver(Intent intent, ServiceReceiver receiver) {
		if (receiver != null)
			intent.putExtra(ServiceReceiver.EXTRA_RECEIVER, receiver);
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
		// send the stored results of previous executions
		while (this.receiver != null && !storedResults.isEmpty()) {
			//noinspection SynchronizeOnNonFinalField
			synchronized (this.receiver) {
				StoreResult result = storedResults.remove(0);
				Intent originalIntent = result.resultData.getParcelable(EXTRA_ORIGINAL_INTENT);
				//noinspection ConstantConditions
				Bundle originalData = originalIntent.getExtras();
				this.receiver.onReceiveResult(result.getResultCode(), result.getResultData(), originalIntent, originalData);
			}
		}
	}

	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		if (receiver != null) {
			Intent originalIntent = resultData.getParcelable(EXTRA_ORIGINAL_INTENT);
			//noinspection ConstantConditions
			Bundle originalData = originalIntent.getExtras();
			receiver.onReceiveResult(resultCode, resultData, originalIntent, originalData);
		} else {
			// stores the results
			storedResults.add(new StoreResult(resultCode, resultData));
		}
	}

	/**
	 * Common interface to receive a result from a service.
	 */
	public interface Receiver {

		public void onReceiveResult(int resultCode, Bundle resultData, Intent originalIntent, Bundle originalBundle);
	}

	class StoreResult {

		private final int resultCode;

		private final Bundle resultData;

		protected StoreResult(int resultCode, Bundle resultData) {
			this.resultCode = resultCode;
			this.resultData = resultData;
		}

		public int getResultCode() {
			return resultCode;
		}

		public Bundle getResultData() {
			return resultData;
		}
	}
}