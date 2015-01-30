package uk.co.imallan.tuchongdaily.api;

import java.io.Serializable;

/**
 * Created by allan on 15/1/30.
 */
public class Response<Data> implements Serializable {

	private Data data;

	private boolean isError;

	private String errorMessage;

	private int errorCode;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
