package com.drools.util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class UserSession {
	private final static KieSession userSession;
	static{
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.newKieClasspathContainer();
		userSession=kieContainer.newKieSession("userSession");
	}
	private UserSession(){
		
	}

	public static KieSession getInstance() {
		return userSession;
	}
	
}
