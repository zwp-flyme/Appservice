package com.service.handlers;

import org.apache.log4j.Logger;

import com.service.pojo.UserInfo;

public class MatchTask extends CommonTask{

	public MatchTask(UserInfo info) {
		super(info);
		logger = Logger.getLogger(MatchTask.class);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
