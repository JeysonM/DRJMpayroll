package payrollcasestudy.entities.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

public class EmployeeView {
	
	public static void createNewEmployee(String employeeId, String name, String address) {
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
        PayrollDatabase.globalPayrollDatabase.addEmployee(employeeIdInt, employee);
        
	}
	
	public static String showAllEmployees() {
		Set<Integer> employeeIds=PayrollDatabase.globalPayrollDatabase.getAllEmployeeIds();
		List<Integer> employeeIdsList = new ArrayList<>(employeeIds);
		Employee employee;
		String allEmployees = "";
		for(int ind=0; ind < employeeIdsList.size() ; ind++)
		{
			employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeIdsList.get(ind));
			allEmployees = allEmployees + employee.getName().toString()+ " - " + employee.getAddress()+"<br>";
		}
        return allEmployees;
	}

}
