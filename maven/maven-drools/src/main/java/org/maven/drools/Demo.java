package org.maven.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Demo {
	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("hellowSession");
        kieSession.fireAllRules();
        kieSession.dispose();
	}
}
