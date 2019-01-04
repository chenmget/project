package com.demo.pub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;

public class Publisher extends Thread {
	
	private Jedis jedis;
	
	public Publisher(Jedis jedis) {
        this.jedis = jedis;
    }
	
	@Override
	public void run() {
		while(true){
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("mychannel", line);   //从 mychannel 的频道上推送消息
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
}
