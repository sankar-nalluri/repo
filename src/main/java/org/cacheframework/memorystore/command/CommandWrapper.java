package org.cacheframework.memorystore.command;

import org.apache.log4j.Logger;
import org.cacheframework.memorystore.annotations.ParamLength;
import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;
import org.cacheframework.memorystore.utilities.Constants;

/**
 * CommandWrapper wraps command class
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class CommandWrapper implements ICommand {
	
	  private static Logger LOG = Logger.getLogger(CommandWrapper.class);

	  private int noOfParams;

	  private final ICommand command;

	  public CommandWrapper(ICommand command) {
	    this.command = command;
	    ParamLength length = command.getClass().getAnnotation(ParamLength.class);
	    if (length != null) {
	      this.noOfParams = length.value();
	    }
	  }

	  
	  /**
	   * @param requestData
	   * @return responseData
	   */	  
	  public ResponseData execute(RequestData request) {
	    if (request.getParams().size() < noOfParams) {
	    	ResponseData error = new ResponseData();
	    	error.setResponseCode(Constants.FAILURE);
	    	error.setOutput("Params are more than allowed");
	    	LOG.error("Params are more than allowed in the command !!!");;
	    	return error;
	    }
	    return command.execute(request);
	  }
}
