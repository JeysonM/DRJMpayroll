package payrollcasestudy.transactions.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeeController {
	private static Transaction addEmployeeTransaction;
	
	public static void createNewEmployeeHourly(String employeeId, String name, String address, String hourlyRate) 
	{
		addEmployeeTransaction = new AddHourlyEmployeeTransaction(Integer.parseInt(employeeId),name,
				address, Double.parseDouble(hourlyRate));       
		addEmployeeTransaction.execute();
	}
	
	public static void createNewEmployeeSalaried(String employeeId, String name, String address, String salary)
	{
		addEmployeeTransaction = new AddSalariedEmployeeTransaction(Integer.parseInt(employeeId), name,
				address, Double.parseDouble(salary));
		addEmployeeTransaction.execute();
	}
	
	public static void createNewEmployeeCommissioned(String employeeId, String name, String address, 
			String monthlySalary, String commissionRate)
	{
		addEmployeeTransaction = new AddCommissionedEmployeeTransaction(Integer.parseInt(employeeId), name,
				address, Double.parseDouble(monthlySalary), Double.parseDouble(commissionRate));
		addEmployeeTransaction.execute();
	}
	
		
	public static List<Employee> showAllEmployees() {
		return PayrollDatabase.globalPayrollDatabase.getEmployees();
	
	}
	
	public static Employee showEmployee(int employeeId) {
		return PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
	}

	

}
