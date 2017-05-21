package payrollcasestudy.transactions.controllers;

import static java.util.Calendar.NOVEMBER;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.boundaries.ConnectionMySQL;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PaymentController {
	private static Repository repository = new ConnectionMySQL();
	
	private static Transaction paymentTransaction;
	private static PaydayTransaction paydayTransaction;
	
	
	
	public static void createPaymentForHourly(String year, String month, String day, String hours, String employeeId) throws SQLException
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
		
	}
	
	public static void createPaymentForSalesReceipt(String year, String month, String day, String amount, String employeeId) throws SQLException
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(amount),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public static void calculateAllPays(String year, String month, String day) throws SQLException
	{
		Calendar payDate = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paydayTransaction  = new PaydayTransaction(payDate);
		paydayTransaction.execute(repository);
		
	}
	
	public static PayCheck getPayCheckFromPayDayTransaction(String employeeId)
	{
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId));
	}
	
	
	
	
	
	
	
	

}
