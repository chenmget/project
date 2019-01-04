package com.demo.pub;

import redis.clients.jedis.Jedis;

public class SubThread extends Thread{
	private final Subscriber subscriber = new Subscriber();
    private final String channel = "mychannel";
    
    private Jedis jedis;
	
	public SubThread(Jedis jedis) {
        this.jedis = jedis;
    }
	
	@Override
	public void run() {
		System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
		 try {
	            jedis.subscribe(subscriber, channel);    //通过subscribe 的api去订阅，入参是订阅者和频道名
	        } catch (Exception e) {
	            System.out.println(String.format("subsrcibe channel error, %s", e));
	        } finally {
	            if (jedis != null) {
	                jedis.close();
	            }
	        }
	}
}
