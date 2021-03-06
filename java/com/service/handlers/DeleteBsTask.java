package com.service.handlers;

import io.netty.channel.Channel;

import org.apache.log4j.Logger;

import com.service.pojo.CommonInfo;
import com.service.pojo.UserInfo;
import com.service.system.SerSystem;

public class DeleteBsTask  extends CommonTask {

	public DeleteBsTask(UserInfo info) {
		super(info);
		logger = Logger.getLogger(DeleteBsTask.class);
	}

	@Override
	public void run() {
		CommonInfo commonInfo = (CommonInfo)info;
		try {
			Channel channel = SerSystem.getChannelGroup().getChannel(commonInfo.getReceiveFrom());
			if(channel!=null&&channel.isActive()){
				int t = SerSystem.getLocalDbService().DeleteBs(info);
				if(t>0){
					SerSystem.getBsCount().cutCount(commonInfo.getValues());
					commonInfo.setValues(null);
					channel.writeAndFlush(commonInfo);
				}
				else if( t==0){
					commonInfo.setValues(null);
					channel.writeAndFlush(commonInfo);
				}
					
			}   
			else
			logger.warn("channel is null, non user ["+commonInfo.getReceiveFrom()+"] had login!");		
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}

}
