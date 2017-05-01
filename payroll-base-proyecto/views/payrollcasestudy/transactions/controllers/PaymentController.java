package payrollcasestudy.transactions.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PaymentController {
	private Transaction paymentTransaction;
	
	public void createPaymentForHourly(String year, String month, String day, String hours, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute();
	}
	
	public void createPaymentForSalesReceipt(String year, String month, String day, String amount, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(amount),Integer.parseInt(employeeId));
		paymentTransaction.execute();
	}
	
	public void createPaymentSalaried(String year, String month, String day)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paymentTransaction = new PaydayTransaction(date);
	    paymentTransaction.execute();
	}
	

}
