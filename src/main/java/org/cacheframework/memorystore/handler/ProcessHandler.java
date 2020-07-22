package org.cacheframework.memorystore.handler;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.cacheframework.memorystore.command.CommandFactory;
import org.cacheframework.memorystore.command.ICommand;
import org.cacheframework.memorystore.request.RequestData;
import org.cacheframework.memorystore.response.ResponseData;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles process in Cache Server
 *  
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class ProcessHandler extends SimpleChannelInboundHandler<String> {
	
	private static Logger LOG = Logger.getLogger(ProcessHandler.class);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		LOG.debug("CacheServer :: Inside channelRead");
        String[] cmd = msg.split(" ");
        RequestData requestData = new RequestData();
        requestData.setCommand(cmd[0]);
        requestData.setParams(Arrays.asList(cmd).stream().skip(1).collect(Collectors.toList()));
        LOG.debug("RequestData :: " + requestData);
        ICommand command = CommandFactory.getCommand(requestData.getCommand());
        ResponseData responseData = command.execute(requestData);
        LOG.debug("ResponseData :: sent to client :: " + responseData);
        Channel channel = ctx.channel();
        channel.write(responseData.toString());	
        channel.flush();
	}
}
