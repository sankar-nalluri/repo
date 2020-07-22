package org.cacheframework.memorystore.response;

/**
 * Response received from server
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class ResponseData {
	
	private int responseCode;
	
	private String output;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "ResponseData [responseCode=" + responseCode + ", output=" + output + "]";
	}

}
