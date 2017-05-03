package payrollcasestudy.transactions.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
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

public class EmployeeController {
	private static Transaction addEmployeeTransaction;
	private static Transaction paymentTransaction;
	private static PaydayTransaction paydayTransaction;
	
	public EmployeeController()
	{
		
	}
	
	public static void createNewEmployeeHourly(String employeeId, String name, String address, String hourlyRate) 
	{
		Transaction addEmployeeTransaction1 = new AddHourlyEmployeeTransaction(Integer.parseInt(employeeId),name,
				address, Double.parseDouble(hourlyRate));       
		addEmployeeTransaction1.execute();
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
	
	public void createPaymentForHourly(String year, String month, String day, String hours, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		Transaction addTimeCard = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		addTimeCard.execute();
		
	}
	
	public void createPaymentForSalesReceipt(String year, String month, String day, String amount, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(amount),Integer.parseInt(employeeId));
		paymentTransaction.execute();
	}
	
	public static PayCheck calculateAllPays(String year, String month, String day,String employeeId)
	{
		Calendar payDate = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paydayTransaction  = new PaydayTransaction(payDate);
		paydayTransaction.execute();
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId));
		
	}

	

}
