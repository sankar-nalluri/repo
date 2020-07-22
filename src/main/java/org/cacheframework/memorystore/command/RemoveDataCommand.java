package org.cacheframework.memorystore.command;

import java.util.List;

import org.cacheframework.memorystore.annotations.Command;
import org.cacheframework.memorystore.annotations.ParamLength;
import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;
import org.cacheframework.memorystore.server.ValueHolder;
import org.cacheframework.memorystore.utilities.Constants;

/**
 * Remove command with one parameter 
 * to be passed. This command makes
 * server remove value against 
 * parameter as key.
 * 
 * Usage: REMOVE <text>
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

@Command("remove")
@ParamLength(1)
public class RemoveDataCommand implements ICommand {

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
	instance.removeValue((String) keyValuePair.get(0));
    ResponseData data = new ResponseData();
    data.setResponseCode(Constants.SUCCESS);
    data.setOutput("Data removed from cache");
    return data;
  }

}
