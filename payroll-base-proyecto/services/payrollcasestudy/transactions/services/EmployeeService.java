package payrollcasestudy.transactions.services;

import java.sql.SQLException;
import java.util.List;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeeService {
	private Repository repository;
	private Transaction addEmployeeTransaction;
	
	public EmployeeService(Repository repository) {
		this.repository = repository;
	}

	public void createNewEmployeeHourly(String employeeId, String name, String address, String hourlyRate) throws SQLException 
	{
		addEmployeeTransaction = new AddHourlyEmployeeTransaction(Integer.parseInt(employeeId), name,
				address, Double.parseDouble(hourlyRate));
		addEmployeeTransaction.execute(repository);
	}
	
	public void createNewEmployeeSalaried(String employeeId, String name, String address, String salary) throws SQLException
	{
		addEmployeeTransaction = new AddSalariedEmployeeTransaction(Integer.parseInt(employeeId), name,
				address, Double.parseDouble(salary));
		addEmployeeTransaction.execute(repository);
	}
	
	public void createNewEmployeeCommissioned(String employeeId, String name, String address, 
			String monthlySalary, String commissionRate) throws SQLException
	{
		addEmployeeTransaction = new AddCommissionedEmployeeTransaction(Integer.parseInt(employeeId), name,
				address, Double.parseDouble(monthlySalary), Double.parseDouble(commissionRate));
		addEmployeeTransaction.execute(repository);
	}
	
		
	public List<Employee> showAllEmployees() {
		return repository.getEmployees();
	
	}
	
	public Employee showEmployee(int employeeId) {
		return repository.getEmployee(employeeId);
	}
}
