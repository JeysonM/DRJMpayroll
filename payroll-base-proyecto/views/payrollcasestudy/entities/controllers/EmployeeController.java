package payrollcasestudy.entities.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;

public class EmployeeController {
		
	public static void createNewEmployee(String employeeId, String name, String address) {
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
        PayrollDatabase.globalPayrollDatabase.addEmployee(employeeIdInt, employee);
        
	}
	
	//all methods of crud
	
		
	public static List<Employee> showAllEmployees() {
		return PayrollDatabase.globalPayrollDatabase.getEmployees();
	
	}
	
	public static Employee showEmployee(int employeeId) {
		return PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
	}

	

}
