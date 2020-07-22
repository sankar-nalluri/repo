package org.cacheframework.memorystore.request;

import java.util.List;

/**
 * Request
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class RequestData {
	
	private String command;
	
	private List<Object> params;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "RequestData [command=" + command + ", params=" + params + "]";
	}

}
