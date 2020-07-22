package org.cacheframework.memorystore.client;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.cacheframework.memorystore.server.CacheServer;
import org.cacheframework.memorystore.utilities.Constants;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit class to test commands
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class TestCommands {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	private final String echoSuccess = "ResponseData [responseCode=1, output=Hello]";
	private final String addSuccess = "ResponseData [responseCode=1, output=Data Added to cache]";
	private final String getSuccess = "ResponseData [responseCode=1, output=hello::world]";
	private final String removeSuccess = "ResponseData [responseCode=1, output=Data removed from cache]";
	private final String getAfterRemoveFailure = "ResponseData [responseCode=0, output=Data not found in cache]";
	
	private static Thread t;

	@BeforeClass
	public static void setUpServer() throws Exception {
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					new CacheServer("localhost", 7883).run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t.start();		
	}
	
	@Before
	public void setUpStreams() throws IOException {
		outContent.flush();
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	/**
	 * Test ECHO command
	 */
	@Test
	public void testEchoCmd1() {
		CacheClient client = new CacheClient();
		client.runClient(Constants.DEFAULT_HOST, Constants.DEFAULT_PORT, "ECHO Hello");
		System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.out.println("Testing Echo Command");
	    assertEquals(echoSuccess, outContent.toString().trim());
	}
	
	/**
	 * Test ADD command
	 */
	@Test
	public void testAddCmd2() {
		CacheClient client = new CacheClient();
		client.runClient(Constants.DEFAULT_HOST, Constants.DEFAULT_PORT, "ADD hello world");
		System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.out.println("Testing Add Command");
	    assertEquals(addSuccess, outContent.toString().trim());
	}

	/**
	 * Test GET command
	 */
	@Test
	public void testGetCmd3() {
		CacheClient client = new CacheClient();
		client.runClient(Constants.DEFAULT_HOST, Constants.DEFAULT_PORT, "GET hello");
		System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.out.println("Testing Get Command");
	    assertEquals(getSuccess, outContent.toString().trim());
	}
	
	/**
	 * Test REMOVE command
	 */
	@Test
	public void testRemoveCmd4() {
		CacheClient client = new CacheClient();
		client.runClient(Constants.DEFAULT_HOST, Constants.DEFAULT_PORT, "REMOVE hello");
		System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.out.println("Testing Remove Command");
	    assertEquals(removeSuccess, outContent.toString().trim());
	}
	
	/**
	 * Test GET after REMOVE command
	 */
	@Test
	public void testGetAfterRemoveCmd5() {
		CacheClient client = new CacheClient();
		client.runClient(Constants.DEFAULT_HOST, Constants.DEFAULT_PORT, "GET animal");
		System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.out.println("Testing Get without value Command");
	    assertEquals(getAfterRemoveFailure, outContent.toString().trim());
	}
	
	/**
	 * Destroy method
	 */
	@AfterClass
	public static void destroy() {
		if(null != t) {
			try {
				t.interrupt();
			} catch(Exception e) {
				
			}
		}
	}
}
