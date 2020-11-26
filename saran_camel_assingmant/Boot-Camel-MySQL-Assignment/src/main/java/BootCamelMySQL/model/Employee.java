package BootCamelMySQL.model;

import java.io.Serializable;

public class Employee implements Serializable{
	private String empId;
	private String empName;
	
	public Employee() {
		super();
	}
	
	public Employee(String empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	
	public String getEmpId() {
		return empId; 
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + "]";
	}
}
