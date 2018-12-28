package com.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {

	private static String redisAddr = "127.0.0.1";// redis服务器ip
	private static int port = 6379;// redis防火墙端口
	private static String AUTH = ""; // redis密码，通过服务器里redis配置文件里设置
	private static int MAX_TOTAL = 300;// 最大连接数
	private static int MAX_IDLE = 200;// 最大空闲连接数
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出
	private static int MAX_WAIT = 10000;
	private static int TIMEOUT = 10000;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool;

	// Jedis连接池配置
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_TOTAL);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			// jedisPool = new JedisPool(config,redisAddr,port,TIMEOUT,AUTH);
			// 有密码的情况
			jedisPool = new JedisPool(config, redisAddr, port, TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取jedis实例
	public synchronized static Jedis getJedis() {
		// 连接池中获取资源
		try {
			if (jedisPool != null) {
				Jedis jedis = jedisPool.getResource();
				return jedis;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 释放jedis和jedisPool资源
	@SuppressWarnings("deprecation")
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            //jedisPool.returnResource(jedis);
        }
    }
    
    @SuppressWarnings("deprecation")
    public static void returnBrokenResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            //jedisPool.returnBrokenResource(jedis);
        }
    }

}
