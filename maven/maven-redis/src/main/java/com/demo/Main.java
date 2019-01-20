package com.demo;

import com.util.RedisUtil;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		RedisUtil.set("1", "test",5);
		Thread.sleep(3000);
		System.out.println("获取"+RedisUtil.get("1"));
		Thread.sleep(3000);
		System.out.println("获取"+RedisUtil.get("1"));
	}
}
