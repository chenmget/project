package com.drools;

import org.kie.api.runtime.KieSession;

import com.drools.model.User;
import com.drools.util.UserSession;

public class Demo {
	public static void main(String[] args) {
		//Demo.demo1();
		Demo.demo2();
	}
	
	//基础规则
//	public static void demo1(){
//		KieSession kieSession=KieManager.demoKieSession();
//        kieSession.fireAllRules();
//        kieSession.dispose();
//	}
	
	//根据model修改规则
	public static void demo2(){
		KieSession kieSession=UserSession.getInstance();
		User u=new User(21);
		kieSession.insert(u);
        kieSession.fireAllRules();
        System.out.println(u.getLevel());
        //kieSession.dispose();
        
        KieSession kieSession1=UserSession.getInstance();
        User u1=new User(18);
        kieSession1.insert(u1);
        kieSession1.fireAllRules();
        System.out.println(u1.getLevel());
        //kieSession1.dispose();
	}

}
