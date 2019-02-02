package demo.service;

import java.io.IOException;

import demo.model.EmployeeVo;

public interface EmployeeService {
	void save(EmployeeVo vo) throws IOException;
	 
    void delete(EmployeeVo vo);
 
    EmployeeVo search(EmployeeVo vo);
 
    String INDEX = "manage";
    String TYPE = "employee";
 
    String NAME = "name";
    String PHONE = "phone";
    String SEX = "sex";
    String SALARY = "salary";
    String POST = "post";
    String DESC = "desc";
    String JOIN_TIME = "joinTime";
}
