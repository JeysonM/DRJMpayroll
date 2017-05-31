package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;

public class Syndicate implements Afiliation{
	private int numberId;
	private double amount;
	private Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();

	public static final Syndicate NO_AFFILIATION = new Syndicate(0,0);
	
	public Syndicate(int numberId, double amount){
		this.numberId = numberId;
		this.amount = amount;
	}

	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharges.get(date);
	}

	public void addServiceCharge(Calendar date, ServiceCharge serviceCharge) {
		this.serviceCharges.put(date, serviceCharge);
	}
	
	public int getNumberId()
	{
		return numberId;
	}

	public Double getDues() {
		return amount;
	}
	
	public double calculateDeduction(PayCheck payCheck)
	{
		double deductions = 0;
		deductions+= calculateNumberOfFridaysInPeriod(payCheck.getPayPeriodStart(), payCheck.getPayPeriodEnd()) * amount;
		for(ServiceCharge serviceCharge : serviceCharges.values())
		{
			if(isInPayPeriod(serviceCharge.getDate(), payCheck)){
				deductions+=serviceCharge.getAmount();
			}
		}
		
		return deductions;
	}

	private int calculateNumberOfFridaysInPeriod(Calendar payPeriodStart, Calendar payPeriodEnd) {
		int numberOfFridays = 0;
		while(!payPeriodStart.after(payPeriodEnd)){
			if(payPeriodStart.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){
				numberOfFridays++;
			}
			payPeriodStart.add(Calendar.DAY_OF_MONTH, 1);
		}
		return numberOfFridays;
	}

	
	
	
}
