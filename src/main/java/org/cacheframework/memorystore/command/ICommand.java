package org.cacheframework.memorystore.command;

import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;

/**
 * Common interface for all commands
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

@FunctionalInterface
public interface ICommand {
  ResponseData execute(RequestData request);
}
