package org.maven.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import model.User;



public class Main {
	
	@Test
    public void testUser() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("userSession");
        User u=new User();
        u.setScore(88);
        kieSession.insert(u);
        kieSession.fireAllRules();
        System.out.println(u.getLevel());
    }
	
	//@Test
    public void testHelloword() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("helloWorldSession");
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

