package com.common;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.WriteConcern;

public class MongoManage {
	private static MongoClient mongoClient;

	static {
		System.out.println("===============MongoDBUtil初始化========================");
		String ip = "192.168.1.75";
		int port = 27017;
		mongoClient = new MongoClient(ip, port);
		// 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
		// boolean auth = db.authenticate(myUserName, myPassword);
		Builder options = new MongoClientOptions.Builder();
		options.cursorFinalizerEnabled(true);
		// options.autoConnectRetry(true);// 自动重连true
		// options.maxAutoConnectRetryTime(10); // the maximum auto connect
		// retry time
		options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
		options.connectTimeout(30000);// 连接超时，推荐>3000毫秒
		options.maxWaitTime(5000); //
		options.socketTimeout(0);// 套接字超时时间，0无限制
		options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out
																	// of
																	// semaphores
																	// to get
																	// db”错误。
		options.writeConcern(WriteConcern.SAFE);//
		options.build();
	}
}
