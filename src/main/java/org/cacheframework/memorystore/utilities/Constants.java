package org.cacheframework.memorystore.utilities;

import java.nio.charset.Charset;

/**
 * Constants class
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class Constants {
	
	public static final int SUCCESS = 1;
	
	public static final int FAILURE = 0;
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static final long POLLING_INTERVAL = 1000;
	
	public static final String SPACE = " ";
	
	public static final String EOL = "\r\n";
	
	public final static String DEFAULT_HOST = "localhost";
	
    public final static int DEFAULT_PORT = 7883;
}
