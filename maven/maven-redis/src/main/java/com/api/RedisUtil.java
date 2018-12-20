package com.api;



import com.common.RedisManager;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	//private static final Logger log = Logger.getLogger(RedisUtil.class);
	public static class JedisStatus{
        /**Jedis实例获取失败*/
        public static final long FAIL_LONG = -5L;
        /**Jedis实例获取失败*/
        public static final int FAIL_INT = -5;
        /**Jedis实例获取失败*/
        public static final String FAIL_STRING = "-5";
    }
	
	 public static String set(String key, String value) {
	        Jedis jedis = RedisManager.getJedis();
	        if(jedis == null){
	            return JedisStatus.FAIL_STRING;
	        }
	         
	        String result = null;
	        try { 
	            result = jedis.set(key, value);
	        } catch (Exception e) {
	            //log.error("设置值失败：" + e.getMessage(), e);
	        	RedisManager.returnBrokenResource(jedis);
	        } finally {
	        	RedisManager.returnResource(jedis);
	        }

	        return result;
	    }
	    
	    /**
	     * 设置值
	     * 
	     * @param key
	     * @param value
	     * @param expire 过期时间，单位：秒
	     * @return -5：Jedis实例获取失败<br/>OK：操作成功<br/>null：操作失败
	     * @author jqlin
	     */
	    public static String set(String key, String value, int expire) {
	        Jedis jedis = RedisManager.getJedis();
	        if(jedis == null){
	            return JedisStatus.FAIL_STRING;
	        }
	        
	        String result = null;
	        try { 
	            result = jedis.set(key, value);
	            jedis.expire(key, expire);
	        } catch (Exception e) {
	            //log.error("设置值失败：" + e.getMessage(), e);
	        	RedisManager.returnBrokenResource(jedis);
	        } finally {
	        	RedisManager.returnResource(jedis);
	        }
	        
	        return result;
	    }
	    
	    /**
	     * 获取值
	     * 
	     * @param key
	     * @return
	     * @author jqlin
	     */
	    public static String get(String key) {
	        Jedis jedis = RedisManager.getJedis();
	        if(jedis == null){
	            return JedisStatus.FAIL_STRING;
	        }
	         
	        String result = null;
	        try { 
	            result = jedis.get(key);
	        } catch (Exception e) {
	            //log.error("获取值失败：" + e.getMessage(), e);
	        	RedisManager.returnBrokenResource(jedis);
	        } finally {
	        	RedisManager.returnResource(jedis);
	        }

	        return result;
	    }
	    
	    /**
	     * 设置key的过期时间
	     * 
	     * @param key
	     * @param value -5：Jedis实例获取失败，1：成功，0：失败
	     * @return
	     * @author jqlin
	     */
	    public static long expire(String key, int seconds) {
	        Jedis jedis = RedisManager.getJedis();
	        if(jedis == null){
	            return JedisStatus.FAIL_LONG;
	        }
	         
	        long result = 0;
	        try { 
	            result = jedis.expire(key, seconds);
	        } catch (Exception e) {
	            //log.error(String.format("设置key=%s的过期时间失败：" + e.getMessage(), key), e);
	        	RedisManager.returnBrokenResource(jedis);
	        } finally {
	        	RedisManager.returnResource(jedis);
	        }

	        return result;
	    }
}
