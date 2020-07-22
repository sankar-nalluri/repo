package org.cacheframework.memorystore.command;

import java.util.List;

import org.cacheframework.memorystore.annotations.Command;
import org.cacheframework.memorystore.annotations.ParamLength;
import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;
import org.cacheframework.memorystore.utilities.Constants;

/**
 * Echo command with one parameter 
 * to be passed. This command makes
 * server echo back data sent.
 * 
 * Usage: ECHO <text>
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

@Command("echo")
@ParamLength(1)
public class EchoCommand implements ICommand {

	/**
	   * 
	   * Execute methiod
	   * 
	   * @param requestData
	   * @return responseData
	   *  
	   */	
  public ResponseData execute(RequestData request) {
     List<Object> params = request.getParams();
     ResponseData data = new ResponseData();
     data.setResponseCode(Constants.SUCCESS);
     data.setOutput((String) params.get(0));
     return data;
  }
  
}
