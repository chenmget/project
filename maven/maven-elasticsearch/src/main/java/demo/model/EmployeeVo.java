package demo.model;

import java.util.Date;
import java.util.Set;

public class EmployeeVo {
	private String id;      // ID
    private String name;    // 姓名
    private String phone;   // 电话
    private Integer sex;    // 性别
    private Integer salary;  // 工资
    private String post;    // 岗位
    private String desc;    // 描述
    private Date joinTime;  // 入职时间
 
    private String keyword;     // 关键字
    private Integer minSalary;  // 最小工资
    private Integer maxSalary;  // 最大工资
    private Date startTime;     // 开始时间
    private Date endTime;       // 结束时间
    private Set<String> ids;    // ID集合
 
    private Object data;        // 查询结果
    private Long total;      // 查询记录总数
    private Integer from;       // 起始位置
    private Integer size;       // 查询条数
 
    public EmployeeVo(Object data, Long total) {
        this.data = data;
        this.total = total;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Set<String> getIds() {
		return ids;
	}

	public void setIds(Set<String> ids) {
		this.ids = ids;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
    
}
