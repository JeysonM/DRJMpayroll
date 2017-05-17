package payrollcasestudy.boundaries;

import payrollcasestudy.entities.Employee;

public interface Repository {
	public void addEmployee(int employeeId, Employee employee);
}
