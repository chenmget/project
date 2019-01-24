package demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.service.EmployeeService;

public class DemoTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
