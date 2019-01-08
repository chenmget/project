package com.drools.util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class KieManager {
	
	static KieServices kieServices = KieServices.Factory.get();
	static KieContainer kieContainer = kieServices.newKieClasspathContainer();
	
	public static KieSession userKieSession(){
		return kieContainer.newKieSession("userSession");
	}
	
	public static KieSession demoKieSession(){
		return kieContainer.newKieSession("demoSession");
	}
}
