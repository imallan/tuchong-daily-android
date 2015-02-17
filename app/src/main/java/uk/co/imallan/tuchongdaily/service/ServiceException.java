package uk.co.imallan.tuchongdaily.service;

/**
 * Exception for the errors inside the services. It contains some generic error codes.
 */
public class ServiceException extends Exception {

	public static final int ERROR_UNKNOWN = -1;

	public static final int ERROR_UNKNOWN_REQUEST = -2;

	public static final int ERROR_NOT_INTERNET_CONNECTION = -3;

	public static final int ERROR_BAD_DATA_FORMAT = -4;

	public static final int ERROR_API_NOT_SUPPORTED_ANYMORE = -5;

	public static final int ERROR_USER_NOT_LOGGED_IN = -6;

	public static final int ERROR_CONTENT_DOES_NOT_EXISTS = -7;

	public static final int ERROR_CONTENT_ALREADY_EXISTS = -8;

	public static final int ERROR_INVALID_INPUT_VALUE = -9;

	private final int errorCode;

	public ServiceException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ServiceException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
