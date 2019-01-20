package demo.service;

import java.io.IOException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;

import demo.model.EmployeeVo;
import demo.util.EsClient;

public class EmployeeServiceImpl implements EmployeeService {
	
	private static TransportClient client=EsClient.getConnection();
	
	public void save(EmployeeVo vo) throws IOException {
		String builder = this.getBuilder(vo);
        if (vo.getId() == null) {
            // 若不传入 ID 则表示新增
            client.prepareIndex(INDEX, TYPE).setSource(builder, XContentType.JSON);
        } else {
            // 若传入 ID 则表示修改
            client.prepareUpdate(INDEX, TYPE, vo.getId()).setDoc(builder).get();
        }
	}
	
	private String getBuilder(EmployeeVo vo) throws IOException {
          
        if (vo.getName() != null) {
            builder.field(NAME, vo.getName());
        }
        if (vo.getPhone() != null) {
            builder.field(PHONE, vo.getPhone());
        }
        if (vo.getPost() != null) {
            builder.field(POST, vo.getPost());
        }
        if (vo.getDesc() != null) {
            builder.field(DESC, vo.getDesc());
        }
        if (vo.getSex() != null) {
            builder.field(SEX, vo.getSex());
        }
        if (vo.getSalary() != null) {
            builder.field(SALARY, vo.getSalary());
        }
        return builder.endObject();
    }
	

	public void delete(EmployeeVo vo) {
		// TODO Auto-generated method stub

	}

	public EmployeeVo search(EmployeeVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
