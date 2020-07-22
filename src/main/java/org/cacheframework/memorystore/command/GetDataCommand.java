package org.cacheframework.memorystore.command;

import java.util.List;

import org.cacheframework.memorystore.annotations.Command;
import org.cacheframework.memorystore.annotations.ParamLength;
import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;
import org.cacheframework.memorystore.server.ValueHolder;
import org.cacheframework.memorystore.utilities.Constants;

/**
 * Get command with one parameter 
 * to be passed. This command makes
 * server to get value for given 
 * parameter as key.
 * 
 * Usage: GET <key>
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

@Command("get")
@ParamLength(1)
public class GetDataCommand implements ICommand {

	/**
	   * 
	   * Execute methiod
	   * 
	   * @param requestData
	   * @return responseData
	   *  
	   */
  public ResponseData execute(RequestData request) {
	ValueHolder instance = ValueHolder.getInstance();
	List<Object> keyValuePair = request.getParams();
	Object value = instance.getValue((String) keyValuePair.get(0));
    ResponseData data = new ResponseData();
    if(null != value) {
	    data.setResponseCode(Constants.SUCCESS);
	    data.setOutput((String) keyValuePair.get(0) + "::" + value);
    } else {
    	data.setResponseCode(Constants.FAILURE);
        data.setOutput("Data not found in cache");
    }
    return data;
  }

}
