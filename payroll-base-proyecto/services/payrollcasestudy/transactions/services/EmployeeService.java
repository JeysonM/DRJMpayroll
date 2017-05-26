package payrollcasestudy.transactions.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.ConnectionMySQL;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

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
