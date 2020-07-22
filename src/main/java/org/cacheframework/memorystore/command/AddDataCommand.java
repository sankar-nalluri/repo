package org.cacheframework.memorystore.command;

import java.util.List;

import org.cacheframework.memorystore.annotations.Command;
import org.cacheframework.memorystore.annotations.ParamLength;
import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;
import org.cacheframework.memorystore.server.ValueHolder;
import org.cacheframework.memorystore.utilities.Constants;

/**
 * Add command with two parameters 
 * to be passed. This command adds key & value
 * in value store maintained by server.
 * 
 * Usage: ADD <key> <value>
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

@Command("add")
@ParamLength(2)
public class AddDataCommand implements ICommand {

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
	instance.putValue((String) keyValuePair.get(0), keyValuePair.get(1));
    ResponseData data = new ResponseData();
    data.setResponseCode(Constants.SUCCESS);
    data.setOutput("Data Added to cache");
    return data;
  }

}
