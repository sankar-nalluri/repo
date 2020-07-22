package org.cacheframework.memorystore.command;

import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;
import org.cacheframework.memorystore.utilities.Constants;

/**
 * Null command returns error
 * data from server.
 * 
 *  
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */


public class NullCommand implements ICommand {
	
	/**
	   * 
	   * Execute methiod
	   * 
	   * @param requestData
	   * @return responseData
	   *  
	   */
	public ResponseData execute(RequestData request) {
		ResponseData error = new ResponseData();
    	error.setResponseCode(Constants.FAILURE);
    	error.setOutput("ERR unknown command '" + request.getCommand() + "'");
    	return error;
	}
}
