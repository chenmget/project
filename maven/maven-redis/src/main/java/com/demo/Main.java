package com.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import com.common.RedisManager;
import com.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class Main {
	
	
	public static void main(String[] args) throws InterruptedException {
//		 RedisUtil.set("1", "test",5000);
//		 Thread.sleep(3000);
//		 System.out.println("获取"+RedisUtil.get("1"));
		// Thread.sleep(3000);
		// System.out.println("获取"+RedisUtil.get("1"));
		// Jedis jedis = RedisManager.getJedis();
		// System.out.println(jedis.exists("1"));
		// System.out.println(jedis.ttl("1"));
//		float min = 1f;
//		float max = 10f;
//		float floatBounded = min + new Random().nextFloat() * (max - min);
//		System.out.println(new BigDecimal(floatBounded).setScale(2, RoundingMode.UP));
		
		
		
		Jedis jedis = RedisManager.getJedis();
		String lockKey="lock";
		String requestId=System.currentTimeMillis()+"";
		RedisUtil.tryGetDistributedLock(jedis, lockKey, requestId, 2000);
		System.out.println("这是主线程在跑。。");
		Thread.sleep(5000);
		RedisUtil.releaseDistributedLock(jedis, lockKey, requestId);
		
		 Thread t1=new Thread(new Runnable() {
			public void run() {
				String lockKey="lock";
				Jedis jedis = RedisManager.getJedis();
				String requestId=System.currentTimeMillis()+"";
				RedisUtil.tryGetDistributedLock(jedis, lockKey, requestId, 6000);
				System.out.println("这是线程在跑");
				RedisUtil.releaseDistributedLock(jedis, lockKey, requestId);
			}
		});
		 t1.start();

	}
}
