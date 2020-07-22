package org.cacheframework.memorystore.server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.cacheframework.memorystore.handler.ProcessHandler;
import org.cacheframework.memorystore.utilities.Constants;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Cache Server Implementation
 * 
 * @author Vijay
 * @version 0.1
 * @since 1.8
 *
 */
public class CacheServer {
	
	private String host;
    private int port;
    
    private static Logger LOG = Logger.getLogger(CacheServer.class);
  
    public CacheServer(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		
		PropertyConfigurator.configure("log4j.properties");
		 
        //Log in console in and log file
        LOG.debug("Log4j Initialized in Cache Server !!!");
 
		String host = args.length > 1
				? args[0] : Constants.DEFAULT_HOST;
		
        int port = args.length > 1
          ? Integer.parseInt(args[1])
          : Constants.DEFAULT_PORT;
          
        System.out.println("\n#######################################################");
  		
  		System.out.println("\t\tNSBCache");
  		
  		System.out.println("\n#######################################################");
 
        System.out.println("\nServer started..... \n");
          
        new CacheServer(host, port).run();
    }
 
    public void run() throws Exception {
    	//Server booting starts here
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) 
                  throws Exception {
                	LOG.debug("Initializing NIO Channel");

                	ChannelPipeline pipeline = ch.pipeline();
	                
	            	pipeline.addLast("decoder", new StringDecoder());
	            	pipeline.addLast("encoder", new StringEncoder()); 
	                pipeline.addLast("handler", new ProcessHandler());
                }
            });
 
            ChannelFuture f = b.bind(host, port).sync();
            f.channel().closeFuture().sync();
            
        } catch(Exception e) {
        	LOG.info("Server stopped");
        } finally {
        
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}