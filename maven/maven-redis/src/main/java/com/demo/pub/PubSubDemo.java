package com.demo.pub;

import com.common.RedisManager;

public class PubSubDemo {
	
	  public static void main(String[] args) {
		  //Jedis jedis=RedisManager.getJedis();
		  
		  SubThread subThread = new SubThread(RedisManager.getJedis());  //订阅者
	      subThread.start();

	      Publisher publisher = new Publisher(RedisManager.getJedis());    //发布者
	      publisher.start();
		  
	}
	
}
