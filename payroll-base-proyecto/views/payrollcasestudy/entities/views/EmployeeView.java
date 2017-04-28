package payrollcasestudy.entities.views;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

public class EmployeeView {
	
	
	public static String formCreateEmployee() {
		return "<html>"
				+ "<h1>Nuevo Empleado</h1>"
				+ "<body>"
				+ "<form method='post' action='/create'>" 
				+ "<label>ID:</label>"
				+ "<input type='number' name='id'>"
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='name'>"
				+ "<label>Direccion:</label>"
				+ "<input type='text' name='address'>"
				+ "<input type='submit' value='crear'>"
				+ "</body>"
				+ "</html>";
	}
	
	public static String createNewEmployee(String employeeId, String name, String address) {
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
        PayrollDatabase.globalPayrollDatabase.addEmployee(employeeIdInt, employee);
        return "Exito al crear";
	}
	
	public static String showEmployee() {
		int employeeId=1;
		Employee employee;
		employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
        return employee.getName().toString()+ " - " + employee.getAddress();
        //return PayrollDatabase.globalPayrollDatabase.getAllEmployeeIds().toString();
	}

}
