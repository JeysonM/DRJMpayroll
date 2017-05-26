package payrollcasestudy.transactions.services;

import static java.util.Calendar.NOVEMBER;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import payrollcasestudy.boundaries.ConnectionMySQL;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PaymentService {
	private Repository repository;
	private Transaction paymentTransaction;
	private PaydayTransaction paydayTransaction;
	
	public PaymentService(Repository repository) {
		this.repository = repository;
	}

	public void createPaymentForHourly(String year, String month, String day, String hours, String employeeId) throws SQLException
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
		
	}
	
	public void createPaymentForSalesReceipt(String year, String month, String day, String amount, String employeeId) throws SQLException
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(amount),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public void calculateAllPays(String year, String month, String day)
	{
		Calendar payDate = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paydayTransaction  = new PaydayTransaction(payDate);
		paydayTransaction.execute(repository);
		
	}
	
	public PayCheck getPayCheckFromPayDayTransaction(String employeeId)
	{
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId));
	}
	
	public List<PayCheck> getAllPayChecksFromPayDayTransaction()
	{
		return paydayTransaction.getAllPayChecks();
	}
	
	
	
	
	
	
	
	

}
