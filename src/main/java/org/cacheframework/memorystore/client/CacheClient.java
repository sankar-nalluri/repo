package org.cacheframework.memorystore.client;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.cacheframework.memorystore.utilities.Constants;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Cache Client Implementation
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class CacheClient {
	
	private static Logger LOG = Logger.getLogger(CacheClient.class);
	
	/**
	 * Cache client main method
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
	
		String host = args.length > 1
				? args[0] : Constants.DEFAULT_HOST;
				
		int port = args.length > 1
		          ? Integer.parseInt(args[1])
		          : Constants.DEFAULT_PORT;
		
		String command = "";
		
		System.out.println("\n#######################################################");
		
		System.out.println("\t\tNSBCache");
		
		System.out.println("\n###################### Usage ##########################");
		
		System.out.println("\t\tECHO <data>");
		
		System.out.println("\t\tADD <key> <value>");
		
		System.out.println("\t\tGET <key>");
		
		System.out.println("\t\tREMOVE <key>");
		
		System.out.println("\nNote: Commands are not case-sensitive. \nBut values are case-sensitive");
		
		System.out.println("#######################################################");
		
		System.out.println("Press Enter to continue or Type \'END\' to exit");
		
		try(Scanner ob = new Scanner(System.in)) {
			while(!(ob.nextLine()).equalsIgnoreCase("END")) {
				System.out.println("Enter Command: ");
				command = ob.nextLine();
				if(!"".equalsIgnoreCase(command) && command.indexOf(' ') != -1) {
					CacheClient client = new CacheClient();
					client.runClient(host, port, command);
				} else {
					System.out.println("Invalid Command");
				}
				System.out.println("Press Enter to continue or Type \'END\' to exit");
			}
		}
	}
	
	/**
	 * Method that starts cache client
	 * @param host
	 * @param port
	 */
	public void runClient(String host, int port, String msg) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
	    try {
	        Bootstrap b = new Bootstrap();
	        b.group(workerGroup);
	        b.channel(NioSocketChannel.class);
	        b.option(ChannelOption.SO_KEEPALIVE, true);
	        b.handler(new ChannelInitializer<SocketChannel>() {

	            @Override
	            public void initChannel(SocketChannel ch) 
	              throws Exception {
	            	LOG.debug("CacheClient :: Initializing client channel !!!");
	                ChannelPipeline pipeline = ch.pipeline();
	                
	                pipeline.addLast("encoder", new StringEncoder()); 
	                pipeline.addLast("decoder", new StringDecoder());
	                pipeline.addLast("handler", new SimpleChannelInboundHandler<String>() {
	    				
	    				@Override
	    				protected void channelRead0(ChannelHandlerContext ctx, String response) throws Exception {
	    					System.out.println("\n" + response + "\n");
	    			        stop(workerGroup);
	    				}
	    			});
	            }
	        });

	        ChannelFuture f = b.connect(host, port).sync();

	        Channel channel = f.channel();
	        		
	        channel.writeAndFlush(msg);
	        
	        channel.closeFuture().sync();
	
	    } catch(Exception e) {
	    	LOG.error("Exception occured while starting client :: ", e);
	    } finally {
	    	workerGroup.shutdownGracefully();
	    }
		
	}
	
	public void stop(EventLoopGroup group) {
	    //you have to close eventLoopGroup as well
	    group.shutdownGracefully();
	}
}
