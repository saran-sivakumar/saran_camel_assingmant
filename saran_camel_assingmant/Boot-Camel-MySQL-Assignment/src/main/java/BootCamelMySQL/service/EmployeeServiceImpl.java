package BootCamelMySQL.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BootCamelMySQL.model.Employee;

@Service
public class EmployeeServiceImpl extends RouteBuilder {

	@Autowired
	DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		}
	

	@Override
	public void configure() throws Exception {

		//INSERT
		from("direct:insert").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception{
				Employee e = exchange.getIn().getBody(Employee.class);
				String query = "INSERT INTO employee(empId,empName) "
						+ "VALUES('" +e.getEmpId()+ "','" +e.getEmpName()+ "')";
				
				exchange.getIn().setBody(query);
			}
		})
		.to("jdbc:dataSource");
		
		
		
		//SELECT
		from("direct:select").setBody(constant("select * from Employee")).to("jdbc:dataSource")
		.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) exchange.getIn()
						.getBody();
				List<Employee> employees = new ArrayList<Employee>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Employee employee = new Employee();
					employee.setEmpId(data.get("empId"));
					employee.setEmpName(data.get("empName"));
					employees.add(employee);
				}
				exchange.getIn().setBody(employees);
			}
		});

	}

}
