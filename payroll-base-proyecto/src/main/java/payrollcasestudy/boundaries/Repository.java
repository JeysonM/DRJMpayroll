package payrollcasestudy.boundaries;

import java.util.ArrayList;
import java.util.List;

import payrollcasestudy.entities.Employee;

public interface Repository {
	public void addEmployee(int employeeId, Employee employee);
	public List<Employee> getEmployees();
}
